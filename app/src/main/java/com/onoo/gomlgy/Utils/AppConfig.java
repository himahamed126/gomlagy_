package com.onoo.gomlgy.Utils;

import android.content.Context;

import com.onoo.gomlgy.Models.AppSettings;

import java.text.DecimalFormat;

public class AppConfig {
    public static AppSettings appSettings;

    public static String BASE_URL = "https://www.gomlgy.com/api/v1/";
    public static String ASSET_URL = "https://www.gomlgy.com/public/";

//    public static String BASE_URL = "http://192.168.0.129/shop/api/v1/";
//    public static String ASSET_URL = "http://192.168.0.129/shop/public/";

    public static String STRIPE_KEY = "pk_test_c6VvBEbwHFdulFZ62q1IQrar";
    public static String BRAINTREE_KEY = "sandbox_pghddbzc_h44cx45wt7g27wmc";
    public  static boolean CASH_ON_DELIVERY = true;
    public  static boolean WALLET_USE = true;

    public static String convertPrice(Context context, Double price) {
        appSettings = new UserPrefs(context).getAppSettingsPreferenceObjectJson("app_settings_response").getData().get(0);
        return appSettings.getCurrency().getSymbol() + new DecimalFormat("#,###.00").format(Double.parseDouble(String.valueOf(price*appSettings.getCurrency().getExchangeRate())));
    }

    public static AppSettings getAppSettings(Context context){
        return new UserPrefs(context).getAppSettingsPreferenceObjectJson("app_settings_response").getData().get(0);
    }
    public static int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}

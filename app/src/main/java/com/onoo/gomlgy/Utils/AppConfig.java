package com.onoo.gomlgy.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.onoo.gomlgy.R;
import com.onoo.gomlgy.models.AppSettings;
import com.onoo.gomlgy.models.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppConfig {
    public static AppSettings appSettings;

    public static String BASE_URL = "https://www.gomlgy.com/api/v1/";
    public static String ASSET_URL = "https://www.gomlgy.com/public/";

//    public static String BASE_URL = "http://192.168.0.129/shop/api/v1/";
//    public static String ASSET_URL = "http://192.168.0.129/shop/public/";

    public static String STRIPE_KEY = "pk_test_c6VvBEbwHFdulFZ62q1IQrar";
    public static String BRAINTREE_KEY = "sandbox_pghddbzc_h44cx45wt7g27wmc";
    public static boolean CASH_ON_DELIVERY = true;
    public static boolean WALLET_USE = true;

    private static final String TAG = "AppConfig";

    public static String convertPrice(Context context, Double price) {
        appSettings = new UserPrefs(context).getAppSettingsPreferenceObjectJson("app_settings_response").getData().get(0);
        return appSettings.getCurrency().getSymbol() + new DecimalFormat("#,###.00").format(Double.parseDouble(String.valueOf(price * appSettings.getCurrency().getExchangeRate())));
    }

    public static String convertPrice2(Context context, Double price) {
        appSettings = new UserPrefs(context)
                .getAppSettingsPreferenceObjectJson("app_settings_response").getData()
                .get(0);
        return appSettings.getCurrency().getSymbol() + new DecimalFormat("#,###")
                .format(Double.parseDouble(String.valueOf(price * appSettings.getCurrency()
                        .getExchangeRate())));
    }

    public static AppSettings getAppSettings(Context context) {
        return new UserPrefs(context).getAppSettingsPreferenceObjectJson("app_settings_response").getData().get(0);
    }

    public static int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    public static void calcLessPrice(double p1, double p2, double p3, TextView tv1, TextView tv2, TextView tv3, Context context) {
        if (p1 < p2 && p1 < p3) {
            tv1.setText(AppConfig.convertPrice2(context, p1));
            tv2.setText(AppConfig.convertPrice2(context, p2));
            tv3.setText(AppConfig.convertPrice2(context, p3));
        } else if (p2 < p1 && p2 < p3) {
            tv1.setText(AppConfig.convertPrice2(context, p2));
            tv2.setText(AppConfig.convertPrice2(context, p1));
            tv3.setText(AppConfig.convertPrice2(context, p3));
        } else if (p3 < p1 && p3 < p2) {
            tv1.setText(AppConfig.convertPrice2(context, p3));
            tv2.setText(AppConfig.convertPrice2(context, p1));
            tv3.setText(AppConfig.convertPrice2(context, p2));
        } else if (p1 > p2 && p1 > p3) {
            tv1.setText(AppConfig.convertPrice2(context, p1));
            tv2.setText(AppConfig.convertPrice2(context, p2));
            tv3.setText(AppConfig.convertPrice2(context, p3));
        } else if (p1 == p2 && p2 == p3 && p1 == p3) {
            tv1.setTextColor(context.getResources().getColor(R.color.carbon_grey_500));
            tv1.setText(AppConfig.convertPrice2(context, p1));
            tv2.setText(AppConfig.convertPrice2(context, p2));
            tv3.setText(AppConfig.convertPrice2(context, p3));
        } else {
            Log.i(TAG, "calcLessPrice: " + p1 + " - " + p2 + " - " + p3 + " - ");
        }
    }

    public static List<Product> mapResponse(List<Product> response) {
        List<Product> products = new ArrayList<>();
        for (Product product : response) {

            List<Double> prices = new ArrayList<>();
            prices.add(product.getUnitPrice());
            prices.add(product.getUnitPrice2());
            prices.add(product.getUnitPrice3());

            Collections.sort(prices);
            product.setPrices(prices);
            product.setThumbnailImage(ASSET_URL + product.getThumbnailImage());
            products.add(product);
        }
        return products;
    }

}

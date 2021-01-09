package com.onoo.gomlgy.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.appcompat.app.AlertDialog;

import com.onoo.gomlgy.Network.response.AppSettingsResponse;
import com.onoo.gomlgy.Network.response.AuthResponse;
import com.google.gson.Gson;
import com.onoo.gomlgy.Network.response.LoginResponse;
import com.onoo.gomlgy.R;

import java.util.Locale;

public class UserPrefs {

    private static final String PREFS_NAME = "com.onoo.cms.UserPrefs";
    private static final String KEY_LANGUAGE = "KEY_LANGUAGE";
    private static SharedPreferences settings;

    private static SharedPreferences.Editor editor;

    public UserPrefs(Context ctx) {
        if (settings == null) {
            settings = ctx.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);
        }
        editor = settings.edit();
    }

    public void setAuthPreferenceObject(AuthResponse authResponse, String key) {
        Gson gson = new Gson();
        String jsonObject = gson.toJson(authResponse);
        editor.putString(key, jsonObject);
        editor.commit();
    }

    public AuthResponse getAuthPreferenceObjectJson(String key) {
        String json = settings.getString(key, "");
        Gson gson = new Gson();
        AuthResponse authResponse = gson.fromJson(json, AuthResponse.class);
        return authResponse;
    }

    public void setAppSettingsPreferenceObject(AppSettingsResponse appSettingsResponse, String key) {
        Gson gson = new Gson();
        String jsonObject = gson.toJson(appSettingsResponse);
        editor.putString(key, jsonObject);
        editor.commit();
    }

    public AppSettingsResponse getAppSettingsPreferenceObjectJson(String key) {
        String json = settings.getString(key, "");
        Gson gson = new Gson();
        AppSettingsResponse appSettingsResponse = gson.fromJson(json, AppSettingsResponse.class);
        return appSettingsResponse;
    }

    public void clearPreference() {
        editor.clear().commit();
    }

    public static void SaveData(String data_Key, String data_Value) {
        editor.putString(data_Key, data_Value);
        editor.commit();
    }

    public static String LoadData(String data_Key) {
        return settings.getString(data_Key, "");
    }


    public void clearUser(String key) {
        editor.remove(key).apply();
    }


    public void showChangeLanguage(Activity activity) {
        String[] list = {"عربي", "English"};
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getString(R.string.choose_Language));

        int index = -1;
        if (LoadData(KEY_LANGUAGE) != null) {
            String language = LoadData(KEY_LANGUAGE);
            switch (language) {
                case "ar":
                    index = 0;
                    break;
                case "en":
                default:
                    index = 1;
            }
        }

        builder.setSingleChoiceItems(list, index, (dialog, which) -> {
            switch (which) {
                case 0:
                    setLocate("ar", activity);
                    activity.recreate();
                    break;
                case 1:
                default:
                    setLocate("en", activity);
                    activity.recreate();
            }
            dialog.dismiss();
        });
        builder.create().show();
    }

    public static void setLocate(String Lang, Activity activity) {
        Locale locale = new Locale(Lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getResources().updateConfiguration(config, activity.getResources().getDisplayMetrics());
        SaveData(KEY_LANGUAGE, Lang);
    }

    public void loadLocate(Activity activity) {
        if (LoadData(KEY_LANGUAGE) != null) {
            String language = LoadData(KEY_LANGUAGE);
            setLocate(language, activity);
        }
    }
}

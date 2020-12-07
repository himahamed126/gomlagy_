package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.AppSettingsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AppSettingsApiInterface {
    @GET("settings")
    Call<AppSettingsResponse> getAppSettings();
}

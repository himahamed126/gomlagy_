package com.onoo.gomlgy.Network.services;

import com.google.gson.JsonObject;
import com.onoo.gomlgy.Network.response.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApiInterface {

    @POST("auth/login")
    Call<AuthResponse> sendLoginCredentials(@Body JsonObject jsonObject);
}

package com.onoo.gomlgy.Network.services;

import com.google.gson.JsonObject;
import com.onoo.gomlgy.Network.response.OrderResponse;
import com.onoo.gomlgy.Network.response.SendEmailOrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SendEmailOrderApiInterface {
    @Headers("Content-Type: application/json")
    @POST("send-email")
    Call<SendEmailOrderResponse> sendEmailOrderRequest(@Header("Authorization") String authHeader, @Body JsonObject jsonObject);
}

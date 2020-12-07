package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.ShippingInfoResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ShippingInfoCreateApiInterface {
    @POST("user/shipping/create")
    Call<ShippingInfoResponse> updateShippingInfo(@Header("Authorization") String authHeader, @Body JsonObject jsonObject);
}

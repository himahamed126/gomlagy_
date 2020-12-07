package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TodaysDealApiInterface {
    @GET("products/todays-deal")
    Call<ProductResponse> getTodaysDeal();
}

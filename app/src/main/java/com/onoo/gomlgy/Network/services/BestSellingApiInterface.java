package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BestSellingApiInterface {
    @GET("products/best-seller")
    Call<ProductResponse> getBestSellingPrdocuts();
}

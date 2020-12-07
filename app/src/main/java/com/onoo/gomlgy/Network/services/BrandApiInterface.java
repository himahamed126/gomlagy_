package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.BrandResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BrandApiInterface {
    @GET("brands")
    Call<BrandResponse> getBrands();
}

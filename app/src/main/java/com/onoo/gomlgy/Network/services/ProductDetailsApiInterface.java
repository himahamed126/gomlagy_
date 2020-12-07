package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.ProductDetialsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ProductDetailsApiInterface {
    @GET
    Call<ProductDetialsResponse> getProductDetails(@Url String url);
}

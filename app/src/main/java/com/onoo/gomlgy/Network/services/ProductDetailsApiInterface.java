package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.ProductDetailsResponse2;
import com.onoo.gomlgy.Network.response.ProductDetialsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ProductDetailsApiInterface {
    @GET Call<ProductDetailsResponse2> getProductDetails(@Url String url);
}

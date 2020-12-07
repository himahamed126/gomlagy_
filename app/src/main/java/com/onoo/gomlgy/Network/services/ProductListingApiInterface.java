package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.ProductListingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ProductListingApiInterface {
    @GET
    Call<ProductListingResponse> getProducts(@Url String url);
}

package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.AddToWishlistResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AddToWishlistApiInterface {
    @Headers("Content-Type: application/json")
    @POST("wishlists")
    Call<AddToWishlistResponse> sendAddToWishlistRequest(@Header("Authorization") String authHeader, @Body JsonObject jsonObject);
}

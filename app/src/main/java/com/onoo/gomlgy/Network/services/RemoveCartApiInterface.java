package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.RemoveCartResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface RemoveCartApiInterface {
    @DELETE
    Call<RemoveCartResponse> removeCartItem(@Header("Authorization") String authHeader, @Url String url);
}

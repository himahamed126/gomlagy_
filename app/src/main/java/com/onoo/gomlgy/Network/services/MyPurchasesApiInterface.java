package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.MyPurchasesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyPurchasesApiInterface {

    @GET("orders-warrenty?")
    Call<MyPurchasesResponse> getMyPurchases(@Query("page") int page, @Query("user_id") int id);
}

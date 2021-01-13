package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.WarrantyResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WarrantyApiServices {
    @GET("all-warrenty?")
    Call<WarrantyResponse> getWarrantyItems(@Query("page") int page, @Query("user_id") int user_id);
}

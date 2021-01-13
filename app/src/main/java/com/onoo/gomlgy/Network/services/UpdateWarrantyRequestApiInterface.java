package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.AddWarrantyRequestResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UpdateWarrantyRequestApiInterface {

    @GET("update-warrenty")
    Call<AddWarrantyRequestResponse> updateWarrantyRequest(
            @Query("warranty_id") int warranty_id,
            @Query("order_id") int order_id,
            @Query("reason") String reason);
}

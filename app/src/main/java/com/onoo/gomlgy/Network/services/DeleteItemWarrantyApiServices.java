package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.WarrantyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DeleteItemWarrantyApiServices {
    @GET("delete-warrenty?")
    Call<WarrantyResponse> deleteWarrantyItem(@Query("warranty_id") int warrantyId);
}

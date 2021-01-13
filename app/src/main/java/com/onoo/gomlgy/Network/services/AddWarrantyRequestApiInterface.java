package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.AddWarrantyRequestResponse;
import com.onoo.gomlgy.Network.response.MyPurchasesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AddWarrantyRequestApiInterface {

    @GET("store-warrenty")
    Call<AddWarrantyRequestResponse> addWarrantyRequest(@Query("order_id") int order_id,
                                                        @Query("reason") String reason);
}

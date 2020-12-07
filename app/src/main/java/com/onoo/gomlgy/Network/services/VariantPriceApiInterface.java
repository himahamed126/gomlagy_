package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.VariantResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface VariantPriceApiInterface {
    @FormUrlEncoded
    @POST("products/variant/price")
    Call<VariantResponse> getVariantPrice(@Field("id") int id, @Field("color") String color, @Field("choice") String choiceArray);
}

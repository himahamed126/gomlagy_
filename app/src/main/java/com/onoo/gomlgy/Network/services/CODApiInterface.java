package com.onoo.gomlgy.Network.services;

import com.google.gson.JsonObject;
import com.onoo.gomlgy.Network.response.OrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CODApiInterface {
    @Headers("Content-Type: application/json")
    @POST("payments/pay/cod")
    Call<OrderResponse> sendPlaceOrderRequest(@Header("Authorization") String authHeader, @Body JsonObject jsonObject);

//    @FormUrlEncoded
//    @POST("payments/pay/cod")
//    Call<OrderResponse> sendPlaceOrderRequest(@Field("Authorization") String authorization,
//                                              @Field("user_id") int userId,
//                                              @Field("payment_type") String paymentType,
//                                              @Field("payment_status") String paymentStatus,
//                                              @Field("grand_total") double grandTotal,
//                                              @Field("coupon_discount") double couponDiscount,
//                                              @Field("coupon_code") String couponCode);
}

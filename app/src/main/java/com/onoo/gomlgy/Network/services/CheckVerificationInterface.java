package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.CheckVerificationResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CheckVerificationInterface {

    @POST("auth/check_verification")
    @FormUrlEncoded
    Call<CheckVerificationResponse> checkEmail(@Field("email") String email);
}

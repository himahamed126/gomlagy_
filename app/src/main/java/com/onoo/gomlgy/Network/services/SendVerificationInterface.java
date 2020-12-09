package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.CheckVerificationResponse;
import com.onoo.gomlgy.Network.response.ForgetPasswordResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SendVerificationInterface {

    @POST("auth/check_code")
    @FormUrlEncoded
    Call<CheckVerificationResponse> sendCode(@Field("code") String code);
}

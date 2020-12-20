package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.AuthResponse;
import com.onoo.gomlgy.Network.response.CheckVerificationResponse;
import com.onoo.gomlgy.Network.response.ForgetPasswordResponse;
import com.onoo.gomlgy.Network.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SendVerificationInterface {

    @POST("auth/check_code")
    @FormUrlEncoded
    Call<AuthResponse> sendCode(@Field("code") String code);
}

package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.ForgetPasswordResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ForgetPasswordInterface {

    @POST("auth/send_code_forget_password")
    @FormUrlEncoded
    Call<ForgetPasswordResponse> sendEmailForget(@Field("email") String email);
}

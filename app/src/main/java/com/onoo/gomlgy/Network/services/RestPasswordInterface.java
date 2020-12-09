package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.ForgetPasswordResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestPasswordInterface {

    @POST("auth/change_password")
    @FormUrlEncoded
    Call<ForgetPasswordResponse> sendNewPassword(@Field("code") String code,
                                                 @Field("new_password") String new_password);
}

package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.google.gson.JsonObject;
import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.SendEmailOrderResponse;
import com.onoo.gomlgy.Network.services.SendEmailOrderApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.SendEmailOrderInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendOrderEmailInteractorImpl extends AbstractInteractor {
    private final String auth_token;
    private final JsonObject jsonObject;
    SendEmailOrderInteractor.CallBack mCallBack;
    private SendEmailOrderApiInterface apiService;


    public SendOrderEmailInteractorImpl(Executor threadExecutor, MainThread mainThread, SendEmailOrderInteractor.CallBack CallBack,
                                        String auth_token, JsonObject jsonObject) {
        super(threadExecutor, mainThread);
        this.mCallBack = CallBack;
        this.auth_token = "Bearer " + auth_token;
        this.jsonObject = jsonObject;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(SendEmailOrderApiInterface.class);

        Call<SendEmailOrderResponse> call = apiService.sendEmailOrderRequest(auth_token, jsonObject);

        call.enqueue(new Callback<SendEmailOrderResponse>() {
            @Override
            public void onResponse(Call<SendEmailOrderResponse> call, Response<SendEmailOrderResponse> response) {
                try {
                    mCallBack.onSendEmailOrderSubmitted(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SendEmailOrderResponse> call, Throwable t) {
                mCallBack.onSendEmailOrderSubmitError();
            }
        });
    }
}

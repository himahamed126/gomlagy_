package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.ShippingInfoResponse;
import com.onoo.gomlgy.Network.services.ShippingInfoCreateApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.ShippingInfoCreateInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingInfoCreateInteractorImpl extends AbstractInteractor {
    private ShippingInfoCreateInteractor.CallBack mCallback;
    private ShippingInfoCreateApiInterface apiService;
    private JsonObject jsonObject;
    private String auth_token;

    public ShippingInfoCreateInteractorImpl(Executor threadExecutor, MainThread mainThread, ShippingInfoCreateInteractor.CallBack callBack, JsonObject jsonObject, String auth_token) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.jsonObject = jsonObject;
        this.auth_token = "Bearer "+auth_token;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(ShippingInfoCreateApiInterface.class);

        Call<ShippingInfoResponse> call = apiService.updateShippingInfo(auth_token, jsonObject);

        call.enqueue(new Callback<ShippingInfoResponse>() {
            @Override
            public void onResponse(Call<ShippingInfoResponse> call, Response<ShippingInfoResponse> response) {
                try {
                    //Log.d("Test", response.body().getVariant());
                    mCallback.onShippingInfoCreated(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ShippingInfoResponse> call, Throwable t) {
                Log.d("Test", t.getMessage());
                mCallback.onShippingInfoCreateError();
            }
        });

    }
}

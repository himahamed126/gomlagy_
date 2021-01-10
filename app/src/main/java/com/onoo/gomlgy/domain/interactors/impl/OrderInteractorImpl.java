package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.services.OrderApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.OrderInteractor;
import com.onoo.gomlgy.Network.response.OrderResponse;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderInteractorImpl extends AbstractInteractor {
    private OrderInteractor.CallBack mCallback;
    private OrderApiInterface apiService;
    private JsonObject jsonObject;
    private String auth_token;

    public OrderInteractorImpl(Executor threadExecutor, MainThread mainThread, OrderInteractor.CallBack callBack, String auth_token, JsonObject jsonObject) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.auth_token = "Bearer " + auth_token;
        this.jsonObject = jsonObject;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(OrderApiInterface.class);

        Call<OrderResponse> call = apiService.sendPlaceOrderRequest(auth_token, jsonObject);

        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                try {
                    //Log.d("Test", response.body().getVariant());
                    mCallback.onOrderSubmitted(response.body());
                } catch (Exception e) {
                    Log.e("Exceptionn", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                //Log.d("Test", String.valueOf(call.isExecuted()));
                mCallback.onOrderSubmitError();
            }
        });

    }
}

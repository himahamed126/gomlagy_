package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.OrderResponse;
import com.onoo.gomlgy.Network.services.CODApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.CODInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CODInteractorImpl extends AbstractInteractor {
    private CODInteractor.CallBack mCallback;
    private CODApiInterface apiService;
    private JsonObject jsonObject;
    private String auth_token;
    private static final String TAG = "paymenttt";

    public CODInteractorImpl(Executor threadExecutor, MainThread mainThread, CODInteractor.CallBack callBack, String auth_token, JsonObject jsonObject) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.auth_token = "Bearer " + auth_token;
        this.jsonObject = jsonObject;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(CODApiInterface.class);


        JsonObject obj = new JsonObject();


        obj.addProperty("shipping_address", "aaa");
        obj.addProperty("user_id", 67);
        obj.addProperty("payment_type", "cash_on_delivery");
        obj.addProperty("payment_status", "unpaid");
        obj.addProperty("grand_total", 222);
        obj.addProperty("coupon_discount", 20);
        obj.addProperty("coupon_code", "");


        Call<OrderResponse> call = apiService.sendPlaceOrderRequest(auth_token, obj);

        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        mCallback.onCODOrderSubmitted(response.body());
                        Log.i(TAG, " r " + new Gson().toJson(response.body()));
                    } else {
                        Log.i(TAG, " e " + response.message());
                        Log.i(TAG, " e " + response.code());
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                //Log.d("Test", String.valueOf(call.isExecuted()));
                mCallback.onCODOrderSubmitError();
                Log.i(TAG, " f " + t.getMessage());
            }
        });
    }

    public class APIError {
        private String message;

        public String getMessage() {
            return message;
        }
    }
}

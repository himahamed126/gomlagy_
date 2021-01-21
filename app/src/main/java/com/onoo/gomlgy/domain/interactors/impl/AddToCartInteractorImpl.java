package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.AddToCartResponse;
import com.onoo.gomlgy.Network.services.AddToCartApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.AddToCartInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddToCartInteractorImpl extends AbstractInteractor {
    private AddToCartInteractor.CallBack mCallback;
    private AddToCartApiInterface apiService;
    private int user_id;
    private int product_id;
    private int quantity;

    private String variant;
    private String auth_token;

    private static final String TAG = "AddToCartt";

    public AddToCartInteractorImpl(Executor threadExecutor, MainThread mainThread, AddToCartInteractor.CallBack callBack, String auth_token, int user_id, int product_id, String variant, int quantity) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.user_id = user_id;
        this.product_id = product_id;
        this.variant = variant;
        this.auth_token = "Bearer " + auth_token;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(AddToCartApiInterface.class);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", user_id);
        jsonObject.addProperty("id", product_id);
        jsonObject.addProperty("variant", variant);
        jsonObject.addProperty("quantity", quantity);


        Call<AddToCartResponse> call = apiService.sendAddToCartRequest(auth_token, jsonObject);

        call.enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {
                try {
                    //Log.d("Test", response.body().getVariant());
                    Log.i(TAG, "product_id : " + product_id + " userId : " + user_id + " variant : " + variant + " quantity : " + quantity);
                    mCallback.onCartItemAdded(response.body());
                } catch (Exception e) {
                    Log.e(TAG + " Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<AddToCartResponse> call, Throwable t) {
                //Log.d("Test", String.valueOf(call.isExecuted()));
                mCallback.onCartItemAddedError();
            }
        });

    }
}

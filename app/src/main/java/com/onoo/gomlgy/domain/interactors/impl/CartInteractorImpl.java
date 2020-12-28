package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.CartResponse;
import com.onoo.gomlgy.Network.services.CartApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.CartInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartInteractorImpl extends AbstractInteractor {
    private CartInteractor.CallBack mCallback;
    private CartApiInterface apiService;
    private int user_id;
    private String token;

    public CartInteractorImpl(Executor threadExecutor, MainThread mainThread, CartInteractor.CallBack callBack, int id, String token) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.user_id = id;
        this.token = "Bearer " + token;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(CartApiInterface.class);
        Call<CartResponse> call = apiService.getCartItems(token, "carts/" + user_id);
        Log.i("dddd", "run: " + token + "---" + user_id);

        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                try {
                    mCallback.onCartLodaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                mCallback.onCartError();
            }
        });

    }
}

package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.RemoveWishlistResponse;
import com.onoo.gomlgy.Network.services.RemoveWishlistApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.RemoveWishlistInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoveWishlistInteractorImpl extends AbstractInteractor {
    private RemoveWishlistInteractor.CallBack mCallback;
    private RemoveWishlistApiInterface apiService;
    private int wishlist_id;
    private String token;

    public RemoveWishlistInteractorImpl(Executor threadExecutor, MainThread mainThread, RemoveWishlistInteractor.CallBack callBack, int wishlist_id, String token) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.wishlist_id = wishlist_id;
        this.token = "Bearer "+token;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(RemoveWishlistApiInterface.class);
        Call<RemoveWishlistResponse> call = apiService.removeWishlistItem(token,"wishlists/"+wishlist_id);

        call.enqueue(new Callback<RemoveWishlistResponse>() {
            @Override
            public void onResponse(Call<RemoveWishlistResponse> call, Response<RemoveWishlistResponse> response) {
                try {
                    mCallback.onWishlistItemRemoved(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<RemoveWishlistResponse> call, Throwable t) {
                mCallback.onWishlistItemRemovedError();
            }
        });

    }
}

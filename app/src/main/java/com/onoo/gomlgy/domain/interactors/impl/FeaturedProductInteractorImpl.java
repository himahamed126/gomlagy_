package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.ProductResponse;
import com.onoo.gomlgy.Network.services.FeaturedProductApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.FeaturedProductInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturedProductInteractorImpl extends AbstractInteractor {
    private FeaturedProductInteractor.CallBack mCallback;
    private FeaturedProductApiInterface apiService;

    public FeaturedProductInteractorImpl(Executor threadExecutor, MainThread mainThread, FeaturedProductInteractor.CallBack callBack) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(FeaturedProductApiInterface.class);
        Call<ProductResponse> call = apiService.getFeaturedPrdocuts();

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                try {
                    mCallback.onFeaturedProductDownloaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                mCallback.onFeaturedProductDownloadError();
            }
        });

    }
}

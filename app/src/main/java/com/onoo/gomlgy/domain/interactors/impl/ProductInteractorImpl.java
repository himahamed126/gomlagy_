package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.ProductResponse;
import com.onoo.gomlgy.Network.services.ProductApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.ProductInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductInteractorImpl extends AbstractInteractor {
    private ProductInteractor.CallBack mCallback;
    private ProductApiInterface apiService;
    private String url;

    public ProductInteractorImpl(Executor threadExecutor, MainThread mainThread, ProductInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(ProductApiInterface.class);
        Call<ProductResponse> call = apiService.getProducts(url);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                try {
                    mCallback.onProductDownloaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                mCallback.onProductDownloadError();
            }
        });

    }
}

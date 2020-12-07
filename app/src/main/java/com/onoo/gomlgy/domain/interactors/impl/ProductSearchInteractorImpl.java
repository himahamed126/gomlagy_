package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.ProductSearchResponse;
import com.onoo.gomlgy.Network.services.ProductSearchApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.ProductSearchInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductSearchInteractorImpl extends AbstractInteractor {
    private ProductSearchInteractor.CallBack mCallback;
    private ProductSearchApiInterface apiService;
    private String url;

    public ProductSearchInteractorImpl(Executor threadExecutor, MainThread mainThread, ProductSearchInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(ProductSearchApiInterface.class);
        Call<ProductSearchResponse> call = apiService.getSearchedProducts(url);

        call.enqueue(new Callback<ProductSearchResponse>() {
            @Override
            public void onResponse(Call<ProductSearchResponse> call, Response<ProductSearchResponse> response) {
                try {
                    mCallback.onProductSearched(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductSearchResponse> call, Throwable t) {
                Log.e("Exception", t.getMessage());
                mCallback.onProductSearchedError();
            }
        });

    }
}

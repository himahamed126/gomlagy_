package com.onoo.gomlgy.domain.interactors.impl;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.ProductDetailsResponse3;
import com.onoo.gomlgy.Network.services.ProductDetailsApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.ProductDetailsInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsInteractorImpl extends AbstractInteractor {
    private ProductDetailsInteractor.CallBack mCallback;
    private ProductDetailsApiInterface apiService;
    private String url;

    public ProductDetailsInteractorImpl(Executor threadExecutor, MainThread mainThread, ProductDetailsInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(ProductDetailsApiInterface.class);
        Call<ProductDetailsResponse3> call = apiService.getProductDetails(url);

        call.enqueue(new Callback<ProductDetailsResponse3>() {
            @Override
            public void onResponse(Call<ProductDetailsResponse3> call, Response<ProductDetailsResponse3> response) {
                try {
                    //Log.d("Mehedi", response.toString());
                    mCallback.onProductDetailsDownloaded(response.body().getData().get(0));
                } catch (Exception e) {
                    //Log.d("Mehedi", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductDetailsResponse3> call, Throwable t) {
                //Log.d("Mehedi", t.getLocalizedMessage());
                mCallback.onProductDetailsDownloadError();
            }
        });

    }
}

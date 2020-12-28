package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.ProductListingResponse;
import com.onoo.gomlgy.Network.services.Cat1ApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.Cat1Interactor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cat1InteractorImpl extends AbstractInteractor {
    private Cat1Interactor.CallBack mCallback;
    private Cat1ApiInterface apiService;
    int subCat;

    public Cat1InteractorImpl(Executor threadExecutor, MainThread mainThread, Cat1Interactor.CallBack callBack, int subCat) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.subCat = subCat;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(Cat1ApiInterface.class);
        String url = "https://www.gomlgy.com/api/v1/get-product?category_id=5&sub_category_id=" + subCat;
        Call<ProductListingResponse> call = apiService.getCat1(url);

        call.enqueue(new Callback<ProductListingResponse>() {
            @Override
            public void onResponse(Call<ProductListingResponse> call, Response<ProductListingResponse> response) {
                try {
                    mCallback.onCat1ProductDownloaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductListingResponse> call, Throwable t) {
                mCallback.onCat1ProductDownloadError();
            }
        });

    }
}

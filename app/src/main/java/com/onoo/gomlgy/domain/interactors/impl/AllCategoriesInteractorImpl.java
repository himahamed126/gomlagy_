package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.models.collectionmodel;
import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.services.getProductsWithSubcategory;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.AllCategoryInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategoriesInteractorImpl extends AbstractInteractor {
    private AllCategoryInteractor.CallBack mCallback;
    private getProductsWithSubcategory apiService;

    public AllCategoriesInteractorImpl(Executor threadExecutor, MainThread mainThread, AllCategoryInteractor.CallBack callBack) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
    }

    @Override
    public void run() {

        apiService = ApiClient.getClient().create(getProductsWithSubcategory.class);
        Call<collectionmodel> getProducts = apiService.get_Products_With_SubCategory("Mobile accessories");

        getProducts.enqueue(new Callback<collectionmodel>() {
            @Override
            public void onResponse(Call<collectionmodel> call, Response<collectionmodel> response) {
                try {
                    mCallback.onAllCategoriesDownloaded(response.body().getData().get(0).getSubCategories());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }
            @Override
            public void onFailure(Call<collectionmodel> call, Throwable t) {
                mCallback.onAllCategoriesDownloadError();
            }
        });
    }
}

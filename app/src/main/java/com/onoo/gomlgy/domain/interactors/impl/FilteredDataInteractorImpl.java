package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.GetFilteredDataResponse;
import com.onoo.gomlgy.Network.services.FiltersInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.FilteredDataInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilteredDataInteractorImpl extends AbstractInteractor {
    private FilteredDataInteractor.CallBack mCallback;
    private FiltersInterface apiService;
    private String categoryId, subCategoryId, maxPrice, minPrice, brandId;

    public FilteredDataInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                      FilteredDataInteractor.CallBack callBack, String categoryId,
                                      String subCategoryId, String maxPrice, String minPrice,
                                      String brandId) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.brandId = brandId;
    }

    @Override
    public void run() {

        apiService = ApiClient.getClient().create(FiltersInterface.class);
        Call<GetFilteredDataResponse> getFilteredProducts = apiService.getFilteredData(categoryId,
                subCategoryId, maxPrice, minPrice, brandId);

        getFilteredProducts.enqueue(new Callback<GetFilteredDataResponse>() {
            @Override
            public void onResponse(Call<GetFilteredDataResponse> call,
                                   Response<GetFilteredDataResponse> response) {
                try {
                    assert response.body() != null;
                    mCallback.onProductsFiltered(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<GetFilteredDataResponse> call, Throwable t) {
                mCallback.onFilteringError();
            }
        });

    }
}

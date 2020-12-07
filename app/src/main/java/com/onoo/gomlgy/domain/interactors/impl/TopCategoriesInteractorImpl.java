package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.CategoryResponse;
import com.onoo.gomlgy.Network.services.TopCategoryApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.TopCategoryInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopCategoriesInteractorImpl extends AbstractInteractor {
    private TopCategoryInteractor.CallBack mCallback;
    private TopCategoryApiInterface apiService;

    public TopCategoriesInteractorImpl(Executor threadExecutor, MainThread mainThread, TopCategoryInteractor.CallBack callBack) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(TopCategoryApiInterface.class);
        Call<CategoryResponse> call = apiService.getTopCategories();

        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                try {
                    mCallback.onTopCategoriesDownloaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                mCallback.onTopCategoriesDownloadError();
            }
        });

    }
}

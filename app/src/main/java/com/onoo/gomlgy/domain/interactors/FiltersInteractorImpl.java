package com.onoo.gomlgy.domain.interactors;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.FiltersResponse;
import com.onoo.gomlgy.Network.services.FiltersInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltersInteractorImpl extends AbstractInteractor {
    private AllFiltersInteractor.CallBack mCallback;
    private FiltersInterface apiService;
    private String categoryId, subCategoryId;

    public FiltersInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                 AllFiltersInteractor.CallBack callBack, String categoryId,
                                 String subCategoryId) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
    }

    @Override
    public void run() {

        apiService = ApiClient.getClient().create(FiltersInterface.class);
        Call<FiltersResponse> getProducts = apiService.getFilters(categoryId, subCategoryId);

        getProducts.enqueue(new Callback<FiltersResponse>() {
            @Override
            public void onResponse(Call<FiltersResponse> call, Response<FiltersResponse> response) {
                try {
                    assert response.body() != null;
                    mCallback.onAllFiltersDownloaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<FiltersResponse> call, Throwable t) {
                mCallback.onAllFiltersDownloadError();
            }
        });

    }
}

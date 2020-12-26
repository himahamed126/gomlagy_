package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.SubCategoryResponse;
import com.onoo.gomlgy.Network.services.SubCategoryApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.SubCategoryInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;
import com.onoo.gomlgy.models.SubCategorymodel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryInteractorImpl extends AbstractInteractor {
    private SubCategoryInteractor.CallBack mCallback;
    private SubCategoryApiInterface apiService;
    private String categoryId;

    public SubCategoryInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                     SubCategoryInteractor.CallBack callBack, String categoryId) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.categoryId = categoryId;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(SubCategoryApiInterface.class);
        Call<SubCategoryResponse> call = apiService.getSubSubcategories(categoryId);

        call.enqueue(new Callback<SubCategoryResponse>() {
            @Override
            public void onResponse(Call<SubCategoryResponse> call, Response<SubCategoryResponse> response) {
                try {

//                    Add an item showing "All products" to the sub categories list
                    List<SubCategorymodel> subCategories = new ArrayList<>();
                    subCategories.add(new SubCategorymodel("All products"));
                    subCategories.addAll(response.body().getData());
                    mCallback.onSubSubCategoriesDownloaded(subCategories);

                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SubCategoryResponse> call, Throwable t) {
                mCallback.onSubSubCategoriesDownloadError();
            }
        });

    }
}

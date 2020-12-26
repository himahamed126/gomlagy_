package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.SubCategoryResponse;
import com.onoo.gomlgy.Network.services.SubSubCategoryApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.SubCategoryInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;
import com.onoo.gomlgy.models.SubCategory;
import com.onoo.gomlgy.models.SubSubCategory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryInteractorImpl extends AbstractInteractor {
    private SubCategoryInteractor.CallBack mCallback;
    private SubSubCategoryApiInterface apiService;
    private String url;

    public SubCategoryInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                     SubCategoryInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(SubSubCategoryApiInterface.class);
        Call<SubCategoryResponse> call = apiService.getSubSubcategories(url);

        call.enqueue(new Callback<SubCategoryResponse>() {
            @Override
            public void onResponse(Call<SubCategoryResponse> call, Response<SubCategoryResponse> response) {
                try {

//                    Add an item showing "All products" to the sub categories list
                    List<SubCategory> subCategories = new ArrayList<>();
                    subCategories.add(new SubCategory("All products"));
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
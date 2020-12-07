package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.BannerResponse;
import com.onoo.gomlgy.Network.services.BannerApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.BannerInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerInteractorImpl extends AbstractInteractor {
    private BannerInteractor.CallBack mCallback;
    private BannerApiInterface apiService;

    public BannerInteractorImpl(Executor threadExecutor, MainThread mainThread, BannerInteractor.CallBack callBack) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(BannerApiInterface.class);
        Call<BannerResponse> call = apiService.getBanners();

        call.enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
                try {
                    mCallback.onBannersDownloaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {
                mCallback.onBannersDownloadError();
            }
        });

    }
}

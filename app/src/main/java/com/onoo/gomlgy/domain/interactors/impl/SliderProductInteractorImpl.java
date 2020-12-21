package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.models.offers_sources.offers.Offers;
import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.services.OffersApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.SliderProductInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SliderProductInteractorImpl extends AbstractInteractor {

    private SliderProductInteractor.CallBack mCallback;
//    private SliderImageApiInterface apiService;
    private OffersApiInterface apiService;

    public SliderProductInteractorImpl(Executor threadExecutor, MainThread mainThread, SliderProductInteractor.CallBack callBack) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(OffersApiInterface.class);
        Call<Offers> call = apiService.getOffers();

        call.enqueue(new Callback<Offers>() {
            @Override
            public void onResponse(Call<Offers> call, Response<Offers> response) {
                try {
                    mCallback.onSliderDownloaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Offers> call, Throwable t) {
                mCallback.onSliderDownloadError();
            }
        });

    }
}
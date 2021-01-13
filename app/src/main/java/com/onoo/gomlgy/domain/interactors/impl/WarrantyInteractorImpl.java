package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.WarrantyResponse;
import com.onoo.gomlgy.Network.services.WarrantyApiServices;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.WarrantyInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WarrantyInteractorImpl extends AbstractInteractor {
    private WarrantyInteractor.CallBack mCallBack;
    private WarrantyApiServices apiServices;
    private int userID;
    int page;
    private static final String TAG = "ssss";

    public WarrantyInteractorImpl(Executor threadExecutor, MainThread mainThread, WarrantyInteractor.CallBack callBack, int page, int userID) {
        super(threadExecutor, mainThread);
        mCallBack = callBack;
        this.userID = userID;
        this.page = page;
    }

    @Override
    public void run() {
        Log.i(TAG, "r");
        apiServices = ApiClient.getClient().create(WarrantyApiServices.class);
        Call<WarrantyResponse> call = apiServices.getWarrantyItems(page, userID);

        call.enqueue(new Callback<WarrantyResponse>() {
            @Override
            public void onResponse(Call<WarrantyResponse> call, Response<WarrantyResponse> response) {

                if (response.isSuccessful()) {
                    mCallBack.onWarrantyLoaded(response.body());
                    Log.i(TAG, "r" + response.body());
                } else {
                    Log.i(TAG, "e" + response.body());
                }

            }

            @Override
            public void onFailure(Call<WarrantyResponse> call, Throwable t) {
                mCallBack.onWarrantyError();
                Log.i(TAG, "f" + t.getMessage());
            }
        });
    }
}

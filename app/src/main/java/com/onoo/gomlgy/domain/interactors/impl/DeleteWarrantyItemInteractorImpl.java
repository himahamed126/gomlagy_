package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.WarrantyResponse;
import com.onoo.gomlgy.Network.services.DeleteItemWarrantyApiServices;
import com.onoo.gomlgy.Network.services.WarrantyApiServices;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.DeleteItemWarrantyInteractor;
import com.onoo.gomlgy.domain.interactors.WarrantyInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteWarrantyItemInteractorImpl extends AbstractInteractor {
    private DeleteItemWarrantyInteractor.CallBack mCallBack;
    private DeleteItemWarrantyApiServices apiServices;
    private int warrantyId;

    private static final String TAG = "ssss";

    public DeleteWarrantyItemInteractorImpl(Executor threadExecutor, MainThread mainThread, DeleteItemWarrantyInteractor.CallBack callBack, int warrantyId) {
        super(threadExecutor, mainThread);
        mCallBack = callBack;
        this.warrantyId = warrantyId;
    }

    @Override
    public void run() {

        apiServices = ApiClient.getClient().create(DeleteItemWarrantyApiServices.class);
        Call<WarrantyResponse> call = apiServices.deleteWarrantyItem(warrantyId);

        call.enqueue(new Callback<WarrantyResponse>() {
            @Override
            public void onResponse(Call<WarrantyResponse> call, Response<WarrantyResponse> response) {

                if (response.isSuccessful()) {
                    mCallBack.onWarrantyDeleted(warrantyId);
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

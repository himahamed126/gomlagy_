package com.onoo.gomlgy.domain.interactors.impl;


import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.AddWarrantyRequestResponse;
import com.onoo.gomlgy.Network.services.UpdateWarrantyRequestApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.UpdateWarrantyRequestInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateWarrantyInteractorImpl extends AbstractInteractor {

    private UpdateWarrantyRequestInteractor.CallBack mCallBack;
    private UpdateWarrantyRequestApiInterface apiInterface;
    private int warrantyId;
    private int orderId;
    private String reason;

    public UpdateWarrantyInteractorImpl(Executor threadExecutor, MainThread mainThread, UpdateWarrantyRequestInteractor.CallBack callBack, int warrantyId, int orderId, String reason) {
        super(threadExecutor, mainThread);
        mCallBack = callBack;
        this.reason = reason;
        this.orderId = orderId;
        this.warrantyId = warrantyId;
    }

    @Override
    public void run() {
        apiInterface = ApiClient.getClient().create(UpdateWarrantyRequestApiInterface.class);

        Call<AddWarrantyRequestResponse> call = apiInterface.updateWarrantyRequest(warrantyId, orderId, reason);

        call.enqueue(new Callback<AddWarrantyRequestResponse>() {
            @Override
            public void onResponse(Call<AddWarrantyRequestResponse> call, Response<AddWarrantyRequestResponse> response) {
                if (response.isSuccessful()) {
                    mCallBack.onUpdateWarrantySubmitted(response.body());
                } else {

                }
            }

            @Override
            public void onFailure(Call<AddWarrantyRequestResponse> call, Throwable t) {
                mCallBack.onUpdateWarrantyError();
            }
        });
    }
}

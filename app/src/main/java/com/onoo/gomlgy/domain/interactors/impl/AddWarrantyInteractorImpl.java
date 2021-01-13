package com.onoo.gomlgy.domain.interactors.impl;


import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.AddWarrantyRequestResponse;
import com.onoo.gomlgy.Network.services.AddWarrantyRequestApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.AddWarrantyRequestInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddWarrantyInteractorImpl extends AbstractInteractor {

    private AddWarrantyRequestInteractor.CallBack mCallBack;
    private AddWarrantyRequestApiInterface apiInterface;
    private int orderId;
    private String reason;

    public AddWarrantyInteractorImpl(Executor threadExecutor, MainThread mainThread, AddWarrantyRequestInteractor.CallBack callBack, int orderId, String reason) {
        super(threadExecutor, mainThread);
        mCallBack = callBack;
        this.reason = reason;
        this.orderId = orderId;
    }

    @Override
    public void run() {
        apiInterface = ApiClient.getClient().create(AddWarrantyRequestApiInterface.class);

        Call<AddWarrantyRequestResponse> call = apiInterface.addWarrantyRequest(orderId, reason);

        call.enqueue(new Callback<AddWarrantyRequestResponse>() {
            @Override
            public void onResponse(Call<AddWarrantyRequestResponse> call, Response<AddWarrantyRequestResponse> response) {
                if (response.isSuccessful()) {
                    mCallBack.onAddWarrantySubmitted(response.body());
                } else {

                }
            }

            @Override
            public void onFailure(Call<AddWarrantyRequestResponse> call, Throwable t) {
                mCallBack.onAddWarrantyError();
            }
        });
    }
}

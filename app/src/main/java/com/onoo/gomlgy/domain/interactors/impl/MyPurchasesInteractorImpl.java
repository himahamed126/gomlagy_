package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.MyPurchasesResponse;
import com.onoo.gomlgy.Network.services.MyPurchasesApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.MyPurchasesInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPurchasesInteractorImpl extends AbstractInteractor {
    MyPurchasesInteractor.CallBack mCallBack;
    MyPurchasesApiInterface apiInterface;
    int userID;
    int page;
    private static final String TAG = "MyPurchases";

    public MyPurchasesInteractorImpl(Executor threadExecutor, MainThread mainThread, MyPurchasesInteractor.CallBack callBack, int page, int userID) {
        super(threadExecutor, mainThread);
        mCallBack = callBack;
        this.userID = userID;
        this.page = page;
    }

    @Override
    public void run() {

        apiInterface = ApiClient.getClient().create(MyPurchasesApiInterface.class);
        Call<MyPurchasesResponse> call = apiInterface.getMyPurchases(page, userID);

        call.enqueue(new Callback<MyPurchasesResponse>() {
            @Override
            public void onResponse(Call<MyPurchasesResponse> call, Response<MyPurchasesResponse> response) {


                if (response.isSuccessful()) {
                    mCallBack.onPurchasesLoaded(response.body());
                    Log.i(TAG, "r" + response.body());
                } else {
                    Log.i(TAG, "e" + response.body());
                }
            }

            @Override
            public void onFailure(Call<MyPurchasesResponse> call, Throwable t) {
                mCallBack.onPurchasesError();
                Log.i(TAG, "f" + t.getMessage());
            }
        });

    }
}

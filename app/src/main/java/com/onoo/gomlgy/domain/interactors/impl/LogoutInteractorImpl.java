package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.LogoutResponse;
import com.onoo.gomlgy.Network.services.LogoutApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.LogoutInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogoutInteractorImpl extends AbstractInteractor {
    private LogoutInteractor.CallBack mCallback;
    private LogoutApiInterface apiService;
    private String auth_token;

    public LogoutInteractorImpl(Executor threadExecutor, MainThread mainThread, LogoutInteractor.CallBack callBack, String auth_token) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.auth_token = "Bearer "+auth_token;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(LogoutApiInterface.class);

        Call<LogoutResponse> call = apiService.sendLogoutRequest(auth_token);

        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                try {
                    //Log.d("Test", response.body().getVariant());
                    mCallback.onLoggedOut(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                //Log.d("Test", String.valueOf(call.isExecuted()));
                mCallback.onLoggedOutError();
            }
        });

    }
}

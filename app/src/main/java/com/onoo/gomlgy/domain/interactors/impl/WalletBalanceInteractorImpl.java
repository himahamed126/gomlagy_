package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.WalletBalanceResponse;
import com.onoo.gomlgy.Network.services.WalletBalanceApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.WalletBalanceInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletBalanceInteractorImpl extends AbstractInteractor {
    private WalletBalanceInteractor.CallBack mCallback;
    private WalletBalanceApiInterface apiService;
    private int user_id;
    private String auth_token;

    public WalletBalanceInteractorImpl(Executor threadExecutor, MainThread mainThread, WalletBalanceInteractor.CallBack callBack, int user_id, String auth_token) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.user_id = user_id;
        this.auth_token = "Bearer "+auth_token;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(WalletBalanceApiInterface.class);

        Call<WalletBalanceResponse> call = apiService.getWalletbalance(auth_token, user_id);

        call.enqueue(new Callback<WalletBalanceResponse>() {
            @Override
            public void onResponse(Call<WalletBalanceResponse> call, Response<WalletBalanceResponse> response) {
                try {
                    mCallback.onWalletBalanceLodaded(response.body().getBalance());
                } catch (Exception e) {
                    Log.e("Wallet Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<WalletBalanceResponse> call, Throwable t) {
                Log.d("Wallet Test", t.getMessage());
                mCallback.onWalletBalanceLoadError();
            }
        });

    }
}

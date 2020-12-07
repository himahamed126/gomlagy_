package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.AuthResponse;
import com.onoo.gomlgy.Network.services.SocialLoginApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.SocialLoginInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SocialLoginInteractorImpl extends AbstractInteractor {
    private SocialLoginInteractor.CallBack mCallback;
    private SocialLoginApiInterface apiService;
    private String id;
    private String name;
    private String email;


    public SocialLoginInteractorImpl(Executor threadExecutor, MainThread mainThread, SocialLoginInteractor.CallBack callBack, String id, String name, String email) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(SocialLoginApiInterface.class);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("remember_me", true);

        Call<AuthResponse> call = apiService.sendSocialLoginCredentials(jsonObject);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                try {
                    //Log.d("Test", response.body().toString());
                    mCallback.onValidLogin(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                //Log.d("Test", String.valueOf(t.getMessage()));
                mCallback.onLoginError();
            }
        });

    }
}

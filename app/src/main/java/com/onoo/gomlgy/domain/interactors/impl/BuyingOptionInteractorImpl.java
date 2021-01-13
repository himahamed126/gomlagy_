package com.onoo.gomlgy.domain.interactors.impl;

import android.util.Log;
import android.widget.Toast;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.VariantResponse;
import com.onoo.gomlgy.Network.services.VariantPriceApiInterface;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.BuyingOptionInteractor;
import com.onoo.gomlgy.domain.interactors.base.AbstractInteractor;
import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyingOptionInteractorImpl extends AbstractInteractor {
    private BuyingOptionInteractor.CallBack mCallback;
    private VariantPriceApiInterface apiService;
    private int id;
    private String color;
    private JsonArray choicesArray;

    private static final String TAG = "BuyingOptionse";

    public BuyingOptionInteractorImpl(Executor threadExecutor, MainThread mainThread, BuyingOptionInteractor.CallBack callBack, int id, String color, JsonArray choicesArray) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.id = id;
        this.color = color;
        this.choicesArray = choicesArray;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(VariantPriceApiInterface.class);
        //Log.i("SentDAta", "run: "id:"+id+"");
        Call<VariantResponse> call = apiService.getVariantPrice(id, color, choicesArray.toString());
      //  Call<VariantResponse> call = apiService.getVariantPrice(137, "#00FFFF", "[{\"section\":\"3\",\"title\":\"model\",\"name\":\"s2\"}]");
       // Log.i("wwwwww", "id : " + id + " color : " + color + " choice : " + choicesArray.toString());
      //  Toast.makeText(, "", Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<VariantResponse>() {
            @Override
            public void onResponse(Call<VariantResponse> call, Response<VariantResponse> response) {
                try {
                    Log.d("Test", response.body().getVariant());
                    mCallback.onGetVariantPrice(response.body());
                    Log.i(TAG, "id : " + id + " color : " + color + " choice : " + choicesArray);
                } catch (Exception e) {
                    Log.i("ffff", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<VariantResponse> call, Throwable t) {
                Log.i("ffff", String.valueOf(call.isExecuted()));
                mCallback.onGetVariantPriceError();
            }
        });

    }
}

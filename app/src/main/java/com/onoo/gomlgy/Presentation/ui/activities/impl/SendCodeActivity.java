package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.CheckVerificationResponse;
import com.onoo.gomlgy.Network.services.SendVerificationInterface;
import com.onoo.gomlgy.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendCodeActivity extends AppCompatActivity {

    TextView tvEmail;
    Button confirmBtn;
    private SendVerificationInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_code);

        init();
        confirmBtn.setOnClickListener(v -> sendEmail());
    }

    void init() {
        tvEmail = findViewById(R.id.input_email);
        confirmBtn = findViewById(R.id.btn_confirm);
        apiService = ApiClient.getClient().create(SendVerificationInterface.class);
    }


    public void sendEmail() {
        if (tvEmail.getText().toString().length() <= 0) {
            Toast.makeText(SendCodeActivity.this, "Enter Email", Toast.LENGTH_LONG).show();
        } else {
            Call<CheckVerificationResponse> call = apiService.sendCode(tvEmail.getText().toString());
            call.enqueue(new Callback<CheckVerificationResponse>() {
                @Override
                public void onResponse(Call<CheckVerificationResponse> call, Response<CheckVerificationResponse> response) {
                    if (response.code() == 200) {
                        if (response.body().getSuccess()) {
                            Toast.makeText(SendCodeActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            Intent returnIntent = new Intent();
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                        } else {
                            Toast.makeText(SendCodeActivity.this, response.message(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(SendCodeActivity.this, response.message(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<CheckVerificationResponse> call, Throwable t) {
                    Toast.makeText(SendCodeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
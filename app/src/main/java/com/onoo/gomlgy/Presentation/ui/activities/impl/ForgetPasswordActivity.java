package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.ForgetPasswordResponse;
import com.onoo.gomlgy.Network.services.ForgetPasswordInterface;
import com.onoo.gomlgy.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {

    TextView tvEmail;
    Button confirmBtn;
    private ForgetPasswordInterface apiService;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        init();
        confirmBtn.setOnClickListener(v -> sendEmail());
    }

    void init() {
        tvEmail = findViewById(R.id.input_email);
        confirmBtn = findViewById(R.id.btn_confirm);
        apiService = ApiClient.getClient().create(ForgetPasswordInterface.class);
    }


    public void sendEmail() {
        if (tvEmail.getText().toString().length() <= 0) {
            Toast.makeText(ForgetPasswordActivity.this, "Enter Email", Toast.LENGTH_LONG).show();
        } else {
            Call<ForgetPasswordResponse> call = apiService.sendEmailForget(tvEmail.getText().toString());
            call.enqueue(new Callback<ForgetPasswordResponse>() {
                @Override
                public void onResponse(Call<ForgetPasswordResponse> call, Response<ForgetPasswordResponse> response) {
                    if (response.code() == 200) {
                        if (response.body().getSuccess()) {
                            startActivity(new Intent(ForgetPasswordActivity.this, ResetPasswordActivity.class));
                        } else {
                            Toast.makeText(ForgetPasswordActivity.this, response.message(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ForgetPasswordResponse> call, Throwable t) {
                    Toast.makeText(ForgetPasswordActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
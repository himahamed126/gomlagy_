package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.ForgetPasswordResponse;
import com.onoo.gomlgy.Network.services.RestPasswordInterface;
import com.onoo.gomlgy.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {

    TextView tvCode, tvNewPassword;
    Button confirmBtn;
    private RestPasswordInterface apiService;
    String code, newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        init();
        confirmBtn.setOnClickListener(v -> sendNewPassword());
    }

    void init() {
        tvCode = findViewById(R.id.input_code);
        tvNewPassword = findViewById(R.id.input_new_pass);
        confirmBtn = findViewById(R.id.btn_confirm);
        apiService = ApiClient.getClient().create(RestPasswordInterface.class);
    }


    public void sendNewPassword() {
        apiService = ApiClient.getClient().create(RestPasswordInterface.class);

        if (tvCode.getText().toString().length() <= 0 && tvNewPassword.getText().toString().length() <= 0) {
            Toast.makeText(ResetPasswordActivity.this, "Enter code", Toast.LENGTH_LONG).show();
        } else {
            Call<ForgetPasswordResponse> call = apiService.sendNewPassword(tvCode.getText().toString(), tvNewPassword.getText().toString());

            call.enqueue(new Callback<ForgetPasswordResponse>() {
                @Override
                public void onResponse(Call<ForgetPasswordResponse> call, Response<ForgetPasswordResponse> response) {
                    if (response.code() == 200) {
                        if (response.body().getSuccess()) {
                            startActivity(new Intent(ResetPasswordActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(ResetPasswordActivity.this, response.message(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ForgetPasswordResponse> call, Throwable t) {
                    Toast.makeText(ResetPasswordActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.AuthResponse;
import com.onoo.gomlgy.Network.response.CheckVerificationResponse;
import com.onoo.gomlgy.Network.services.CheckVerificationInterface;
import com.onoo.gomlgy.Presentation.presenters.LoginPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.LoginView;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.CustomToast;
import com.onoo.gomlgy.Utils.UserPrefs;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements LoginView {

    private TextView tvEmail;
    private TextView tvPassword, link_signUp, link_forgotPassword;
    private Button bLogin;
    private CallbackManager callbackManager;
    private Button fb, google;
    private LoginButton loginButton;
    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton signInButton;

    private CheckVerificationInterface apiService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeActionBar();
        setTitle(getString(R.string.my_account));
        initviews();

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = tvEmail.getText().toString();
                String password = tvPassword.getText().toString();

                new LoginPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), LoginActivity.this).validLogin(email, password);
            }
        });

        link_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        link_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgetPasswordActivity.class));
            }
        });

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject json, GraphResponse response) {
                        // Application code
                        if (response.getError() != null) {
                            System.out.println("ERROR");
                        } else {
                            System.out.println("Success");
                            String jsonresult = String.valueOf(json);
                            Log.d("mylog", "JSON Result" + jsonresult);

                            new LoginPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), LoginActivity.this).validSocialLogin(json.optString("id"), json.optString("name"), json.optString("email"));
                        }
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();

                //CustomToast.showToast(LoginActivity.this, "Login Successful", R.color.colorSuccess);
            }

            @Override
            public void onCancel() {
                CustomToast.showToast(LoginActivity.this, getString(R.string.login_canceled), R.color.colorWarning);
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("FaceBook Response :", exception.getLocalizedMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 200) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initviews() {
        tvEmail = findViewById(R.id.input_email);
        tvPassword = findViewById(R.id.input_password);
        bLogin = findViewById(R.id.btn_login);
        link_signUp = findViewById(R.id.link_signUp);
        link_forgotPassword = findViewById(R.id.link_forgotPassword);
        fb = (Button) findViewById(R.id.fb);
        loginButton = (LoginButton) findViewById(R.id.login_button);

        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email", "public_profile");

        apiService = ApiClient.getClient().create(CheckVerificationInterface.class);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                loginButton.performClick();
            }
        });

        google = findViewById(R.id.google);
        signInButton = findViewById(R.id.sign_in_button);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 200);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 200);
            }
        });
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            //CustomToast.showToast(LoginActivity.this, "Login Successful", R.color.colorSuccess);

            new LoginPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), LoginActivity.this).validSocialLogin(account.getId(), account.getDisplayName(), account.getEmail());

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("Google Login", String.valueOf(e.getMessage()));
            CustomToast.showToast(LoginActivity.this, e.getMessage(), R.color.colorDanger);
        }
    }

    @Override
    public void setLoginResponse(AuthResponse authResponse) {
        apiService.checkEmail("himahamed999@gmail.com").enqueue(new Callback<CheckVerificationResponse>() {
            @Override
            public void onResponse(Call<CheckVerificationResponse> call, Response<CheckVerificationResponse> response) {
                if (response.code() == 200) {
                    if (response.body().getMessage().equals("Non Verified")) {
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(LoginActivity.this, SendCodeActivity.class));
                    } else {
                        UserPrefs userPrefs = new UserPrefs(getApplicationContext());
                        userPrefs.setAuthPreferenceObject(authResponse, "auth_response");
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CheckVerificationResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}


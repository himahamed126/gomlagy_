package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.Network.response.AuthResponse;
import com.onoo.gomlgy.Presentation.ui.activities.LoginView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.LoginInteractor;
import com.onoo.gomlgy.domain.interactors.SocialLoginInteractor;
import com.onoo.gomlgy.domain.interactors.impl.LoginInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.SocialLoginInteractorImpl;

public class LoginPresenter extends AbstractPresenter implements LoginInteractor.CallBack, SocialLoginInteractor.CallBack {

    private LoginView loginView;

    public LoginPresenter(Executor executor, MainThread mainThread, LoginView loginView) {
        super(executor, mainThread);
        this.loginView = loginView;
    }

    public void validLogin(String email, String password) {
        new LoginInteractorImpl(mExecutor, mMainThread, this, email, password).execute();
    }

    public void validSocialLogin(String id, String name, String email) {
        new SocialLoginInteractorImpl(mExecutor, mMainThread, this, id, name, email).execute();
    }

    @Override
    public void onValidLogin(AuthResponse authResponse) {
        if (loginView != null){
            loginView.setLoginResponse(authResponse);
        }
    }

    @Override
    public void onLoginError() {

    }
}

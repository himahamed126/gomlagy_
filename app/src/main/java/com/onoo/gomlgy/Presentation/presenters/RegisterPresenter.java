package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.Network.response.RegistrationResponse;
import com.onoo.gomlgy.Presentation.ui.activities.RegisterView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.RegisterInteractor;
import com.onoo.gomlgy.domain.interactors.impl.RegisterInteractorImpl;
import com.google.gson.JsonObject;

public class RegisterPresenter extends AbstractPresenter implements RegisterInteractor.CallBack {

    private RegisterView registerView;

    public RegisterPresenter(Executor executor, MainThread mainThread, RegisterView registerView) {
        super(executor, mainThread);
        this.registerView = registerView;
    }

    public void validateRegistration(JsonObject jsonObject) {
        new RegisterInteractorImpl(mExecutor, mMainThread, this, jsonObject).execute();
    }

    @Override
    public void onRegistrationDone(RegistrationResponse registrationResponse) {
        if (registerView != null){
            registerView.setRegistrationResponse(registrationResponse);
        }
    }

    @Override
    public void onRegistrationError() {

    }
}

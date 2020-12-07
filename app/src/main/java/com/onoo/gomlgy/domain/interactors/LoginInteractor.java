package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.AuthResponse;

public interface LoginInteractor {
    interface CallBack {

        void onValidLogin(AuthResponse authResponse);

        void onLoginError();
    }
}

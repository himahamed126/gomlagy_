package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.AuthResponse;

public interface SocialLoginInteractor {
    interface CallBack {

        void onValidLogin(AuthResponse authResponse);

        void onLoginError();
    }
}

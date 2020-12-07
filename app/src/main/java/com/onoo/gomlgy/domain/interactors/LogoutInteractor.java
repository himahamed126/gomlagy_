package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.LogoutResponse;

public interface LogoutInteractor {
    interface CallBack {

        void onLoggedOut(LogoutResponse logoutResponse);

        void onLoggedOutError();
    }
}

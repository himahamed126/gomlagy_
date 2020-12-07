package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.RegistrationResponse;

public interface RegisterInteractor {
    interface CallBack {

        void onRegistrationDone(RegistrationResponse registrationResponse);

        void onRegistrationError();
    }
}

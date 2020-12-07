package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.ProfileInfoUpdateResponse;

public interface ProfileInfoUpdateInteractor {
    interface CallBack {

        void onProfileInfoUpdated(ProfileInfoUpdateResponse profileInfoUpdateResponse);

        void onProfileInfoUpdatedError();
    }
}

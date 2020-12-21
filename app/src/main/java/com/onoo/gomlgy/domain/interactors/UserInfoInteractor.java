package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.User;

public interface UserInfoInteractor {
    interface CallBack {

        void onUserInfoLodaded(User user);

        void onUserInfoError();
    }
}

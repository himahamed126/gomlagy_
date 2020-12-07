package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Models.User;

public interface UserInfoInteractor {
    interface CallBack {

        void onUserInfoLodaded(User user);

        void onUserInfoError();
    }
}

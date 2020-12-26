package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.UserBid;

import java.util.List;

public interface UserBidInteractor {
    interface CallBack {

        void onUserBidLodaded(List<UserBid> userBids);

        void onUserBidError();
    }
}

package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.RemoveCartResponse;

public interface RemoveCartInteractor {
    interface CallBack {

        void onCartItemRemoved(RemoveCartResponse removeCartResponse);

        void onCartItemRemovedError();
    }
}

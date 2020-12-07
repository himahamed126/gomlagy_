package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.OrderResponse;

public interface WalletInteractor {
    interface CallBack {

        void onWalletOrderSubmitted(OrderResponse orderResponse);

        void onWalletOrderSubmitError();
    }
}

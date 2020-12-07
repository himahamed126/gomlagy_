package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.OrderResponse;

public interface CODInteractor {
    interface CallBack {

        void onCODOrderSubmitted(OrderResponse orderResponse);

        void onCODOrderSubmitError();
    }
}

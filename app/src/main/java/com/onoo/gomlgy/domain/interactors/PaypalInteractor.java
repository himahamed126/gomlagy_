package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.OrderResponse;

public interface PaypalInteractor {
    interface CallBack {

        void onPayaplOrderSubmitted(OrderResponse orderResponse);

        void onPayaplOrderSubmitError();
    }
}

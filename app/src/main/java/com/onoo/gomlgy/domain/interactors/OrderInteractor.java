package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.OrderResponse;

public interface OrderInteractor {
    interface CallBack {

        void onOrderSubmitted(OrderResponse orderResponse);

        void onOrderSubmitError();
    }
}

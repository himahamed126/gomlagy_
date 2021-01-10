package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.OrderResponse;
import com.onoo.gomlgy.Network.response.SendEmailOrderResponse;

public interface SendEmailOrderInteractor {

    interface CallBack {

        void onSendEmailOrderSubmitted(SendEmailOrderResponse sendEmailOrderResponse);

        void onSendEmailOrderSubmitError();
    }
}

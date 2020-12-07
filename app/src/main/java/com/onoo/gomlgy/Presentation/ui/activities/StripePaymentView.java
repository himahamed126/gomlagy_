package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.Network.response.StripeClientSecretResponse;

public interface StripePaymentView {
    void onClientSecretReceived(StripeClientSecretResponse stripeClientSecretResponse);
}

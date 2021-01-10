package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.Network.response.CouponResponse;
import com.onoo.gomlgy.Network.response.OrderResponse;
import com.onoo.gomlgy.Network.response.SendEmailOrderResponse;

public interface PaymentView {
    void onCouponApplied(CouponResponse couponResponse);
    void onOrderSubmitted(OrderResponse orderResponse);
    void onSendEmailOrderSubmitted(SendEmailOrderResponse sendEmailOrderResponse);
}

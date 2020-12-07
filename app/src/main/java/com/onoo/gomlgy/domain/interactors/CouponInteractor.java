package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.CouponResponse;

public interface CouponInteractor {
    interface CallBack {

        void onCouponApplied(CouponResponse couponResponse);

        void onCouponAppliedError();
    }
}

package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.VariantResponse;

public interface BuyingOptionInteractor {
    interface CallBack {

        void onGetVariantPrice(VariantResponse variantResponse);

        void onGetVariantPriceError();
    }
}

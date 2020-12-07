package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.AddToCartResponse;

public interface AddToCartInteractor {
    interface CallBack {

        void onCartItemAdded(AddToCartResponse addToCartResponse);

        void onCartItemAddedError();
    }
}

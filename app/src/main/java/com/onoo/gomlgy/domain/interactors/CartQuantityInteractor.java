package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.CartQuantityUpdateResponse;

public interface CartQuantityInteractor {
    interface CallBack {

        void onCartQuantityUpdated(CartQuantityUpdateResponse cartQuantityUpdateResponse);

        void onCartQuantityUpdatedError();
    }
}

package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Models.CartModel;

import java.util.List;

public interface CartInteractor {
    interface CallBack {

        void onCartLodaded(List<CartModel> cartModel);

        void onCartError();
    }
}

package com.onoo.gomlgy.Presentation.ui.listeners;

import com.onoo.gomlgy.models.CartModel;

public interface CartItemListener {
    void onCartRemove(CartModel cartModel);
    void onQuantityUpdate(int quantity, CartModel cartModel);
}

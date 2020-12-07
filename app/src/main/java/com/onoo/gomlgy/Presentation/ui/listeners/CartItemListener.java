package com.onoo.gomlgy.Presentation.ui.listeners;

import com.onoo.gomlgy.Models.CartModel;

public interface CartItemListener {
    void onCartRemove(CartModel cartModel);
    void onQuantityUpdate(int quantity, CartModel cartModel);
}

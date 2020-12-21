package com.onoo.gomlgy.Presentation.ui.fragments;

import com.onoo.gomlgy.models.CartModel;
import com.onoo.gomlgy.Network.response.CartQuantityUpdateResponse;
import com.onoo.gomlgy.Network.response.RemoveCartResponse;

import java.util.List;

public interface CartView {
    void setCartItems(List<CartModel> cartItems);
    void showRemoveCartMessage(RemoveCartResponse removeCartResponse);
    void showCartQuantityUpdateMessage(CartQuantityUpdateResponse cartQuantityUpdateResponse);
}

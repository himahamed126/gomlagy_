package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.models.CartModel;
import com.onoo.gomlgy.Network.response.CartQuantityUpdateResponse;
import com.onoo.gomlgy.Network.response.RemoveCartResponse;
import com.onoo.gomlgy.Presentation.ui.fragments.CartView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.CartInteractor;
import com.onoo.gomlgy.domain.interactors.CartQuantityInteractor;
import com.onoo.gomlgy.domain.interactors.RemoveCartInteractor;
import com.onoo.gomlgy.domain.interactors.impl.CartInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.CartQuantityInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.RemoveCartInteractorImpl;

import java.util.List;

public class CartPresenter extends AbstractPresenter implements CartInteractor.CallBack, RemoveCartInteractor.CallBack, CartQuantityInteractor.CallBack {
    private CartView cartView;

    public CartPresenter(Executor executor, MainThread mainThread, CartView cartView) {
        super(executor, mainThread);
        this.cartView = cartView;
    }

    public void getCartItems(int id, String token) {
        new CartInteractorImpl(mExecutor, mMainThread, this, id, token).execute();
    }

    public void removeCartItem(int id, String token){
        new RemoveCartInteractorImpl(mExecutor, mMainThread, this, id, token).execute();
    }

    public void updateCartQuantity(int id, int quantity, String token) {
        new CartQuantityInteractorImpl(mExecutor, mMainThread, this, id, quantity, token).execute();
    }

    @Override
    public void onCartLodaded(List<CartModel> cartModels) {
        if(cartView != null){
            cartView.setCartItems(cartModels);
        }
    }

    @Override
    public void onCartError() {

    }

    @Override
    public void onCartItemRemoved(RemoveCartResponse removeCartResponse) {
        if(cartView != null){
            cartView.showRemoveCartMessage(removeCartResponse);
        }
    }

    @Override
    public void onCartItemRemovedError() {

    }

    @Override
    public void onCartQuantityUpdated(CartQuantityUpdateResponse cartQuantityUpdateResponse) {
        if(cartView != null){
            cartView.showCartQuantityUpdateMessage(cartQuantityUpdateResponse);
        }
    }

    @Override
    public void onCartQuantityUpdatedError() {

    }
}

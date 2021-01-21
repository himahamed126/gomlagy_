package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.Network.response.AddToCartResponse;
import com.onoo.gomlgy.Network.response.VariantResponse;
import com.onoo.gomlgy.Presentation.ui.activities.BuyingOptionView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.AddToCartInteractor;
import com.onoo.gomlgy.domain.interactors.BuyingOptionInteractor;
import com.onoo.gomlgy.domain.interactors.impl.AddToCartInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.BuyingOptionInteractorImpl;
import com.google.gson.JsonArray;


public class BuyingOptionPresenter extends AbstractPresenter implements BuyingOptionInteractor.CallBack, AddToCartInteractor.CallBack {
    private BuyingOptionView buyingOptionView;

    public BuyingOptionPresenter(Executor executor, MainThread mainThread, BuyingOptionView buyingOptionView) {
        super(executor, mainThread);
        this.buyingOptionView = buyingOptionView;
    }

    public void getVariantPrice(int id, String color, JsonArray choicesArray, int quantity) {
        new BuyingOptionInteractorImpl(mExecutor, mMainThread, this, id, color, choicesArray, quantity).execute();
    }

    public void addToCart(String token, int user_id, int product_id, String variant, int quantity) {
        new AddToCartInteractorImpl(mExecutor, mMainThread, this, token, user_id, product_id, variant, quantity).execute();
    }

    @Override
    public void onGetVariantPrice(VariantResponse variantResponse) {
        if (buyingOptionView != null) {
            buyingOptionView.setVariantprice(variantResponse);
        }
    }

    @Override
    public void onGetVariantPriceError() {

    }

    @Override
    public void onCartItemAdded(AddToCartResponse addToCartResponse) {
        if (buyingOptionView != null) {
            buyingOptionView.setAddToCartMessage(addToCartResponse);
        }
    }

    @Override
    public void onCartItemAddedError() {

    }
}

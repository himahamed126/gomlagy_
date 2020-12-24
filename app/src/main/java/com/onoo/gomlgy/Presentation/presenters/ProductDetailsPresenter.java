package com.onoo.gomlgy.Presentation.presenters;

import com.google.gson.JsonArray;
import com.onoo.gomlgy.Models.Product;
import com.onoo.gomlgy.Models.ProductDetails2;
import com.onoo.gomlgy.Network.response.AddToCartResponse;
import com.onoo.gomlgy.Network.response.AddToWishlistResponse;
import com.onoo.gomlgy.Network.response.CheckWishlistResponse;
import com.onoo.gomlgy.Network.response.RemoveWishlistResponse;
import com.onoo.gomlgy.Network.response.VariantResponse;
import com.onoo.gomlgy.Presentation.ui.activities.ProductDetailsView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.AddToCartInteractor;
import com.onoo.gomlgy.domain.interactors.AddToWishlistInteractor;
import com.onoo.gomlgy.domain.interactors.BuyingOptionInteractor;
import com.onoo.gomlgy.domain.interactors.CheckWishlistInteractor;
import com.onoo.gomlgy.domain.interactors.ProductDetailsInteractor;
import com.onoo.gomlgy.domain.interactors.ProductInteractor;
import com.onoo.gomlgy.domain.interactors.RemoveWishlistInteractor;
import com.onoo.gomlgy.domain.interactors.impl.AddToCartInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.AddToWishlistInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.BuyingOptionInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.CheckWishlistInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.ProductDetailsInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.ProductInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.RemoveWishlistInteractorImpl;

import java.util.List;

public class ProductDetailsPresenter extends AbstractPresenter implements ProductDetailsInteractor.CallBack, ProductInteractor.CallBack, AddToCartInteractor.CallBack, AddToWishlistInteractor.CallBack, CheckWishlistInteractor.CallBack, RemoveWishlistInteractor.CallBack, BuyingOptionInteractor.CallBack {
    private ProductDetailsView productDetailsView;
    private int type = 0;

    public ProductDetailsPresenter(Executor executor, MainThread mainThread, ProductDetailsView productDetailsView) {
        super(executor, mainThread);
        this.productDetailsView = productDetailsView;
    }

    public void getProductDetails(String url) {
        new ProductDetailsInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }

    public void getRelatedProducts(String url) {
        type = 0;
        new ProductInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }

    public void getTopSellingProducts(String url) {
        type = 1;
        new ProductInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }

    public void addToCart(String token, int user_id, int product_id, String variant, int quantity) {
        new AddToCartInteractorImpl(mExecutor, mMainThread, this, token, user_id, product_id, variant, quantity).execute();
    }

    public void addToWishlist(String token, int user_id, int product_id) {
        new AddToWishlistInteractorImpl(mExecutor, mMainThread, this, token, user_id, product_id).execute();
    }

    public void checkOnWishlist(String token, int user_id, int product_id) {
        new CheckWishlistInteractorImpl(mExecutor, mMainThread, this, token, user_id, product_id).execute();
    }

    public void removeFromWishlist(String token, int wishlist_id) {
        new RemoveWishlistInteractorImpl(mExecutor, mMainThread, this, wishlist_id, token).execute();
    }


    @Override
    public void onProductDetailsDownloaded(ProductDetails2 productDetails) {
        if (productDetailsView != null) {
            productDetailsView.setProductDetails(productDetails);
        }
    }

    @Override
    public void onProductDetailsDownloadError() {

    }

    @Override
    public void onProductDownloaded(List<Product> products) {
        if (productDetailsView != null && type == 0) {
            productDetailsView.setRelatedProducts(products);
        } else if (productDetailsView != null && type == 1) {
            productDetailsView.setTopSellingProducts(products);
        }
    }

    @Override
    public void onProductDownloadError() {

    }

    @Override
    public void onCartItemAdded(AddToCartResponse addToCartResponse) {
        if (productDetailsView != null) {
            productDetailsView.setAddToCartMessage(addToCartResponse);
        }
    }

    @Override
    public void onCartItemAddedError() {

    }

    @Override
    public void onWishlistItemAdded(AddToWishlistResponse addToWishlistResponse) {
        if (productDetailsView != null) {
            productDetailsView.setAddToWishlistMessage(addToWishlistResponse);
        }
    }

    @Override
    public void onWishlistItemAddedError() {

    }

    @Override
    public void onWishlistChecked(CheckWishlistResponse checkWishlistResponse) {
        if (productDetailsView != null) {
            productDetailsView.onCheckWishlist(checkWishlistResponse);
        }
    }

    @Override
    public void onWishlistCheckedError() {

    }

    @Override
    public void onWishlistItemRemoved(RemoveWishlistResponse removeWishlistResponse) {
        if (productDetailsView != null) {
            productDetailsView.onRemoveFromWishlist(removeWishlistResponse);
        }
    }

    @Override
    public void onWishlistItemRemovedError() {

    }

    public void getVariantPrice(int id, String color, JsonArray choicesArray) {
        new BuyingOptionInteractorImpl(mExecutor, mMainThread, this, id, color, choicesArray).execute();
    }

    @Override
    public void onGetVariantPrice(VariantResponse variantResponse) {
        if (productDetailsView != null) {
            productDetailsView.setVariantprice(variantResponse);
        }
    }

    @Override
    public void onGetVariantPriceError() {

    }
}

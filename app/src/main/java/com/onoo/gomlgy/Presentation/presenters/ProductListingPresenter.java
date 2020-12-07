package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.Network.response.ProductListingResponse;
import com.onoo.gomlgy.Presentation.ui.activities.ProductListingView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.ProductListingInteractor;
import com.onoo.gomlgy.domain.interactors.impl.ProductListingInteractorImpl;

public class ProductListingPresenter extends AbstractPresenter implements ProductListingInteractor.CallBack {
    private ProductListingView productListingView;

    public ProductListingPresenter(Executor executor, MainThread mainThread, ProductListingView productListingView) {
        super(executor, mainThread);
        this.productListingView = productListingView;
    }

    public void getProducts(String url) {
        new ProductListingInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }


    @Override
    public void onProductDownloaded(ProductListingResponse productListingResponse) {
        if (productListingView != null){
            productListingView.setProducts(productListingResponse);
        }
    }

    @Override
    public void onProductDownloadError() {

    }
}

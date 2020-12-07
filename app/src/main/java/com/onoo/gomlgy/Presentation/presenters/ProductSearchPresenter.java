package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.Network.response.ProductSearchResponse;
import com.onoo.gomlgy.Presentation.ui.fragments.ProductSearchView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.ProductSearchInteractor;
import com.onoo.gomlgy.domain.interactors.impl.ProductSearchInteractorImpl;

public class ProductSearchPresenter extends AbstractPresenter implements ProductSearchInteractor.CallBack {
    private ProductSearchView ProductSearchView;

    public ProductSearchPresenter(Executor executor, MainThread mainThread, ProductSearchView ProductSearchView) {
        super(executor, mainThread);
        this.ProductSearchView = ProductSearchView;
    }

    public void getSearchedProducts(String url) {
        new ProductSearchInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }


    @Override
    public void onProductSearched(ProductSearchResponse productSearchResponse) {
        if (ProductSearchView != null){
            ProductSearchView.setSearchedProduct(productSearchResponse);
        }
    }

    @Override
    public void onProductSearchedError() {

    }
}

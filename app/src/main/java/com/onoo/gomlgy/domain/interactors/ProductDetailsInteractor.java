package com.onoo.gomlgy.domain.interactors;


import com.onoo.gomlgy.models.ProductDetails3.ProductDetails3;

public interface ProductDetailsInteractor {
    interface CallBack {

        void onProductDetailsDownloaded(ProductDetails3 productDetails);

        void onProductDetailsDownloadError();
    }
}

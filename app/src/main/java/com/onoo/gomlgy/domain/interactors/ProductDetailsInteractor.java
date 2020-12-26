package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.ProductDetails2;

public interface ProductDetailsInteractor {
    interface CallBack {

        void onProductDetailsDownloaded(ProductDetails2 productDetails);

        void onProductDetailsDownloadError();
    }
}

package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Models.ProductDetails;

public interface ProductDetailsInteractor {
    interface CallBack {

        void onProductDetailsDownloaded(ProductDetails productDetails);

        void onProductDetailsDownloadError();
    }
}

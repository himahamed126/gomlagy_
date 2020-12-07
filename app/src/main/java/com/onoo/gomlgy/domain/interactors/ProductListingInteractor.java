package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.ProductListingResponse;

public interface ProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(ProductListingResponse productListingResponse);

        void onProductDownloadError();
    }
}

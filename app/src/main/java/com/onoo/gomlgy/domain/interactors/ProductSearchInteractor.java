package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.ProductSearchResponse;

public interface ProductSearchInteractor {
    interface CallBack {

        void onProductSearched(ProductSearchResponse productSearchResponse);

        void onProductSearchedError();
    }
}

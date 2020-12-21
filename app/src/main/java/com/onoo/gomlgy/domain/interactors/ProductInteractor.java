package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.Product;

import java.util.List;

public interface ProductInteractor {
    interface CallBack {

        void onProductDownloaded(List<Product> products);

        void onProductDownloadError();
    }
}

package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.Product;

import java.util.List;

public interface Cat3Interactor {
    interface CallBack {

        void onCat3ProductDownloaded(List<Product> products);

        void onCat3ProductDownloadError();
    }
}

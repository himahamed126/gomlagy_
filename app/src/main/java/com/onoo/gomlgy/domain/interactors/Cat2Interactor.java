package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.Product;

import java.util.List;

public interface Cat2Interactor {
    interface CallBack {

        void onCat2ProductDownloaded(List<Product> products);

        void onCat2ProductDownloadError();
    }
}

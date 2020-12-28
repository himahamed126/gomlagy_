package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.Product;

import java.util.List;

public interface Cat1Interactor {
    interface CallBack {

        void onCat1ProductDownloaded(List<Product> products);

        void onCat1ProductDownloadError();
    }
}

package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.Product;

import java.util.List;

public interface Cat4Interactor {
    interface CallBack {

        void onCat4ProductDownloaded(List<Product> products);

        void onCat4ProductDownloadError();
    }
}

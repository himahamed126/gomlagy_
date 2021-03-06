package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.Product;

import java.util.List;

public interface FeaturedProductInteractor {
    interface CallBack {

        void onFeaturedProductDownloaded(List<Product> products);

        void onFeaturedProductDownloadError();
    }
}

package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Models.Product;

import java.util.List;

public interface BestSellingInteractor {
    interface CallBack {

        void onBestSellingProductDownloaded(List<Product> products);

        void onBestSellingProductDownloadError();
    }
}

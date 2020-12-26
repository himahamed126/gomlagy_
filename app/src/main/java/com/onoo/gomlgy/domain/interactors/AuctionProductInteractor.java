package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.AuctionProduct;

import java.util.List;

public interface AuctionProductInteractor {
    interface CallBack {

        void onAuctionProductDownloaded(List<AuctionProduct> auctionProducts);

        void onAuctionProductDownloadError();
    }
}

package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.AuctionBidResponse;

public interface AuctionBidInteractor {
    interface CallBack {

        void onBidSubmitted(AuctionBidResponse auctionBidResponse);

        void onBidSubmittedError();
    }
}

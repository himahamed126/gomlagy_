package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.FlashDeal;

public interface FlashDealInteractor {
    interface CallBack {

        void onFlashDealProductDownloaded(FlashDeal flashDeal);

        void onFlashDealProductDownloadError();
    }
}
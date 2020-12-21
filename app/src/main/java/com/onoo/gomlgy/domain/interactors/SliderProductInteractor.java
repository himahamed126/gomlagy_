package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Models.offers_sources.offers.OffersData;

import java.util.List;

public interface SliderProductInteractor {

    interface CallBack {

        void onSliderDownloaded(List<OffersData> offersList);

        void onSliderDownloadError();
    }
}
package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.offers_sources.offers.OffersData;

import java.util.List;

public interface SliderInteractor {

    interface CallBack {

        void onSliderDownloaded(List<OffersData> offersList);

        void onSliderDownloadError();
    }
}

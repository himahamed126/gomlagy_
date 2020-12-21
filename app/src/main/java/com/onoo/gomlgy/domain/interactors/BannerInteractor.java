package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.Banner;

import java.util.List;

public interface BannerInteractor {
    interface CallBack {

        void onBannersDownloaded(List<Banner> banners);

        void onBannersDownloadError();
    }
}

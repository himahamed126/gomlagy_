package com.onoo.gomlgy.domain.interactors;


import com.onoo.gomlgy.models.Brand;

import java.util.List;

public interface BrandInteractor {
    interface CallBack {

        void onBrandsDownloaded(List<Brand> brands);

        void onBrandsDownloadError();
    }
}

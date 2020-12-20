package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Models.SubCategorymodel;

import java.util.List;

public interface AllCategoryInteractor {
    interface CallBack {

        void onAllCategoriesDownloaded(List<SubCategorymodel> categories);

        void onAllCategoriesDownloadError();
    }
}

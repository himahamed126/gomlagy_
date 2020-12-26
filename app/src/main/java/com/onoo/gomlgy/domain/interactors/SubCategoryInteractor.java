package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.SubCategorymodel;

import java.util.List;

public interface SubCategoryInteractor {
    interface CallBack {

        void onSubSubCategoriesDownloaded(List<SubCategorymodel> subCategories);

        void onSubSubCategoriesDownloadError();
    }
}

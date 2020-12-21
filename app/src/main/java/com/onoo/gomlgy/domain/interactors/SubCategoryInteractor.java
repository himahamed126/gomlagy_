package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.SubCategory;

import java.util.List;

public interface SubCategoryInteractor {
    interface CallBack {

        void onSubSubCategoriesDownloaded(List<SubCategory> subCategories);

        void onSubSubCategoriesDownloadError();
    }
}

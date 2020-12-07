package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Models.SubCategory;

import java.util.List;

public interface SubSubCategoryInteractor {
    interface CallBack {

        void onSubSubCategoriesDownloaded(List<SubCategory> subCategories);

        void onSubSubCategoriesDownloadError();
    }
}

package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.Category;

import java.util.List;

public interface TopCategoryInteractor {
    interface CallBack {

        void onTopCategoriesDownloaded(List<Category> categories);

        void onTopCategoriesDownloadError();
    }
}

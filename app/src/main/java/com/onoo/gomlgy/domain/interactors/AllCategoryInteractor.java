package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Models.Category;

import java.util.List;

public interface AllCategoryInteractor {
    interface CallBack {

        void onAllCategoriesDownloaded(List<Category> categories);

        void onAllCategoriesDownloadError();
    }
}

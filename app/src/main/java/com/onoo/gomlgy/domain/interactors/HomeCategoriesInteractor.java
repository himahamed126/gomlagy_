package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.Category;

import java.util.List;

public interface HomeCategoriesInteractor {
    interface CallBack {

        void onHomeCategoriesDownloaded(List<Category> categories);

        void onHomeCategoriesDownloadError();
    }
}

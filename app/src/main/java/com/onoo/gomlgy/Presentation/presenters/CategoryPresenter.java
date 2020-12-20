package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.Models.Category;
import com.onoo.gomlgy.Models.SubCategorymodel;
import com.onoo.gomlgy.Presentation.ui.fragments.CategoryView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.AllCategoryInteractor;
import com.onoo.gomlgy.domain.interactors.impl.AllCategoriesInteractorImpl;

import java.util.List;

public class CategoryPresenter extends AbstractPresenter implements AllCategoryInteractor.CallBack {

    private CategoryView categoryView;

    public CategoryPresenter(Executor executor, MainThread mainThread, CategoryView categoryView) {
        super(executor, mainThread);
        this.categoryView = categoryView;
    }

    public void getAllCategories() {
        new AllCategoriesInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    @Override
    public void onAllCategoriesDownloaded(List<SubCategorymodel> categories) {
        if (categoryView != null) {
            categoryView.setAllCategories(categories);
        }
    }

    @Override
    public void onAllCategoriesDownloadError() {

    }
}

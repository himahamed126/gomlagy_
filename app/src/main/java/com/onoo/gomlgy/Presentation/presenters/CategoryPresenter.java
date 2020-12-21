package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.models.Category;
import com.onoo.gomlgy.Presentation.ui.fragments.CategoryView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.AllCategoryInteractor;
import com.onoo.gomlgy.domain.interactors.impl.AllCategoriesInteractorImpl;

import java.util.Collections;
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
    public void onAllCategoriesDownloaded(List<Category> categories) {
        if (categoryView != null) {
//            Sort categories with id
            Collections.sort(categories, (categoryModel1, categoryModel2) ->
                    (int) (categoryModel1.getId() - categoryModel2.getId()));
            categoryView.setAllCategories(categories);
        }
    }

    @Override
    public void onAllCategoriesDownloadError() {

    }
}

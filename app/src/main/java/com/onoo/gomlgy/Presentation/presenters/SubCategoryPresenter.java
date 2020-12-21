package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.models.SubCategory;
import com.onoo.gomlgy.Presentation.ui.activities.SubCategoryView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.SubCategoryInteractor;
import com.onoo.gomlgy.domain.interactors.impl.SubCategoryInteractorImpl;

import java.util.List;

public class SubCategoryPresenter extends AbstractPresenter implements SubCategoryInteractor.CallBack {
    private SubCategoryView subCategoryView;

    public SubCategoryPresenter(Executor executor, MainThread mainThread, SubCategoryView subSubCategoryView) {
        super(executor, mainThread);
        this.subCategoryView = subSubCategoryView;
    }

    public void getSubSubCategories(String url) {
        new SubCategoryInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }

    @Override
    public void onSubSubCategoriesDownloaded(List<SubCategory> subCategories) {
        if (subCategoryView != null) {
            subCategoryView.setSubCategories(subCategories);
        }
    }

    @Override
    public void onSubSubCategoriesDownloadError() {

    }
}

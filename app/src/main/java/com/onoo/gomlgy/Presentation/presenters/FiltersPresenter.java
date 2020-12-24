package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.Network.response.FiltersResponse;
import com.onoo.gomlgy.Presentation.ui.activities.FiltersView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.AllFiltersInteractor;
import com.onoo.gomlgy.domain.interactors.FiltersInteractorImpl;
import com.onoo.gomlgy.models.FilterData;

public class FiltersPresenter extends AbstractPresenter implements AllFiltersInteractor.CallBack {

    private FiltersView filtersView;

    public FiltersPresenter(Executor executor, MainThread mainThread, FiltersView filtersView) {
        super(executor, mainThread);
        this.filtersView = filtersView;
    }

    public void getFilterData(String categoryId, String subCategoryId) {
        new FiltersInteractorImpl(mExecutor, mMainThread, this, categoryId,
                subCategoryId).execute();
    }

    @Override
    public void onAllFiltersDownloaded(FilterData filterData) {
        if (filtersView != null)
            filtersView.setFilters(filterData);
    }

    @Override
    public void onAllFiltersDownloadError() {

    }
}

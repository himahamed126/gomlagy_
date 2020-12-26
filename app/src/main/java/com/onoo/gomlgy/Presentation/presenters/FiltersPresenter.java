package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.Presentation.ui.activities.FiltersView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.AllFiltersInteractor;
import com.onoo.gomlgy.domain.interactors.FilteredDataInteractor;
import com.onoo.gomlgy.domain.interactors.impl.FilteredDataInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.FiltersInteractorImpl;
import com.onoo.gomlgy.models.FilterData;
import com.onoo.gomlgy.models.Product;

import java.util.List;

public class FiltersPresenter extends AbstractPresenter implements AllFiltersInteractor.CallBack,
        FilteredDataInteractor.CallBack {

    private FiltersView filtersView;

    public FiltersPresenter(Executor executor, MainThread mainThread, FiltersView filtersView) {
        super(executor, mainThread);
        this.filtersView = filtersView;
    }

    public void getFilterData(String categoryId, String subCategoryId) {
        new FiltersInteractorImpl(mExecutor, mMainThread, this, categoryId,
                subCategoryId).execute();
    }

    public void getFilteredProducts(String categoryId, String subCategoryId, String maxPrice,
                                    String minPrice, String brandId) {
        new FilteredDataInteractorImpl(mExecutor, mMainThread, this, categoryId,
                subCategoryId, maxPrice, minPrice, brandId).execute();
    }

    @Override
    public void onAllFiltersDownloaded(FilterData filterData) {
        if (filtersView != null)
            filtersView.setFilters(filterData);
    }

    @Override
    public void onAllFiltersDownloadError() {

    }

    @Override
    public void onProductsFiltered(List<Product> filteredData) {
        if (filtersView != null)
            filtersView.setProducts(filteredData);
    }

    @Override
    public void onFilteringError() {

    }
}

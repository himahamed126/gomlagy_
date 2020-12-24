package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.FilterData;
import com.onoo.gomlgy.models.Productmodel;

import java.util.List;

public interface FilteredDataInteractor {
    interface CallBack {

        void onProductsFiltered(List<Productmodel> filteredData);

        void onFilteringError();
    }
}

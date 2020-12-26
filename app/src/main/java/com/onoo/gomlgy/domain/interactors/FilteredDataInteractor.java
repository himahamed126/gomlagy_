package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.Product;

import java.util.List;

public interface FilteredDataInteractor {
    interface CallBack {

        void onProductsFiltered(List<Product> filteredData);

        void onFilteringError();
    }
}

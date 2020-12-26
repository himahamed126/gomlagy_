package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.models.FilterData;
import com.onoo.gomlgy.models.Product;

import java.util.List;

public interface FiltersView {
    void setFilters(FilterData filterData);

    void setProducts(List<Product> products);
}

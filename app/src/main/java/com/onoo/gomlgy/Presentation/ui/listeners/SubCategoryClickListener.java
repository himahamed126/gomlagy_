package com.onoo.gomlgy.Presentation.ui.listeners;

import com.onoo.gomlgy.models.Product;
import com.onoo.gomlgy.models.Productmodel;

public interface SubCategoryClickListener {

    void onAllProductsClicked();

    void onSeeAllProductsOfSubCategoryClicked(int position);

    void onProductClicked(Productmodel product);

}

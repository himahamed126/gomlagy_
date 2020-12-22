package com.onoo.gomlgy.Presentation.ui.listeners;

import com.onoo.gomlgy.models.Product;

public interface SubCategoryClickListener {

    void onAllProductsClicked();

    void onSeeAllProductsOfSubCategoryClicked(int position);

    void onProductClicked(Product product);

}

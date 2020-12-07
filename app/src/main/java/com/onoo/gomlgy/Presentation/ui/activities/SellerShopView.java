package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.Models.Product;
import com.onoo.gomlgy.Models.Shop;

import java.util.List;

public interface SellerShopView {
    void onShopDetailsLoaded(Shop shop);
    void setFeaturedProducts(List<Product> products);
    void setTopSellingProducts(List<Product> products);
    void setNewProducts(List<Product> products);
}

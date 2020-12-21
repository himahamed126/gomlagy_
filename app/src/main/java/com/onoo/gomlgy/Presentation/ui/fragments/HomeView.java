package com.onoo.gomlgy.Presentation.ui.fragments;

import com.onoo.gomlgy.models.AuctionProduct;
import com.onoo.gomlgy.models.Brand;
import com.onoo.gomlgy.models.Category;
import com.onoo.gomlgy.models.Product;
import com.onoo.gomlgy.models.offers_sources.offers.OffersData;
import com.onoo.gomlgy.Network.response.AppSettingsResponse;
import com.onoo.gomlgy.Network.response.AuctionBidResponse;

import java.util.List;

public interface HomeView {
    void onAppSettingsLoaded(AppSettingsResponse appSettingsResponse);

    void setSliderImages(List<OffersData> sliderImages);

    void setHomeCategories(List<Category> categories);

    void setTodaysDeal(List<Product> products);


    void setBestSelling(List<Product> products);

    void setFeaturedProducts(List<Product> products);

    void setPopularBrands(List<Brand> brands);

    void setAuctionProducts(List<AuctionProduct> auctionProducts);

    void onAuctionBidSubmitted(AuctionBidResponse auctionBidResponse);
}
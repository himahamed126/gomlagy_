package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.Network.response.ProductListingResponse;
import com.onoo.gomlgy.models.offers_sources.offers.OffersData;

import java.util.List;

public interface ProductListingView {

    void initView();

    void getIntentExtras();

    void setProducts(ProductListingResponse productListingResponse);

    void setSliderImages(List<OffersData> sliderImages);

}

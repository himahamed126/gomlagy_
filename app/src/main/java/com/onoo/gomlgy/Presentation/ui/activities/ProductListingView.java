package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.models.offers_sources.offers.OffersData;
import com.onoo.gomlgy.Network.response.ProductListingResponse;

import java.util.List;

public interface ProductListingView {
    void setProducts(ProductListingResponse productListingResponse);

    void setSliderImages(List<OffersData> sliderImages);

}

package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.Network.response.AddToCartResponse;
import com.onoo.gomlgy.Network.response.VariantResponse;

public interface BuyingOptionView {
    void setVariantprice(VariantResponse variantResponse);
    void setAddToCartMessage(AddToCartResponse addToCartResponse);
}

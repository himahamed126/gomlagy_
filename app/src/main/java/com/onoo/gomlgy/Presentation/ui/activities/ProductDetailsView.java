package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.Network.response.AddToCartResponse;
import com.onoo.gomlgy.Network.response.AddToWishlistResponse;
import com.onoo.gomlgy.Network.response.CheckWishlistResponse;
import com.onoo.gomlgy.Network.response.RemoveWishlistResponse;
import com.onoo.gomlgy.Network.response.VariantResponse;
import com.onoo.gomlgy.models.Product;
import com.onoo.gomlgy.models.ProductDetails2;
import com.onoo.gomlgy.models.ProductDetails3.ProductDetails3;

import java.util.List;

public interface ProductDetailsView {
    void setProductDetails(ProductDetails3 productDetails);

    void setRelatedProducts(List<Product> relatedProducts);

    void setTopSellingProducts(List<Product> topSellingProducts);

    void setAddToCartMessage(AddToCartResponse addToCartResponse);

    void setAddToWishlistMessage(AddToWishlistResponse addToWishlistMessage);

    void onCheckWishlist(CheckWishlistResponse checkWishlistResponse);

    void onRemoveFromWishlist(RemoveWishlistResponse removeWishlistResponse);

    void setVariantprice(VariantResponse variantResponse);
}

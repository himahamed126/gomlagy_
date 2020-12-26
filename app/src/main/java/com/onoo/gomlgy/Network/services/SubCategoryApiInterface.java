package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.SubCategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SubCategoryApiInterface {
    @GET("sub-category-products")
    Call<SubCategoryResponse> getSubSubcategories(@Query("category_id") String categoryId);
}

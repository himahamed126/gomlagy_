package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AllCategoryApiInterface {
    @GET("categories")
    Call<CategoryResponse> getAllCategories();
}

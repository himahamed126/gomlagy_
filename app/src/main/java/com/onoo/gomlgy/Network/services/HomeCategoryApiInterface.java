package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeCategoryApiInterface {
    @GET("home-categories")
    Call<CategoryResponse> getHomeCategories();
}

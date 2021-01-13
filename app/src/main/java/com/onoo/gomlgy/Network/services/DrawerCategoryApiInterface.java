package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.CatDrawerResponse;
import com.onoo.gomlgy.models.collectionmodel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DrawerCategoryApiInterface {
    @GET("sub-category-categories")
    Call<CatDrawerResponse> getCategory(@Query("category_id") int  categoryID);
}

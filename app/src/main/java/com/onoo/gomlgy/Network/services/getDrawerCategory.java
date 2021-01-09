package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.CatDrawerResponse;
import com.onoo.gomlgy.models.collectionmodel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface getDrawerCategory {
    @GET("sub-category-categories")
    Call<CatDrawerResponse> get_Category(@Query("category_id") int  categoryID);
}

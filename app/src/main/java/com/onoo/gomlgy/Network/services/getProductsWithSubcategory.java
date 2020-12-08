package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Models.DAtanum;
import com.onoo.gomlgy.Models.collectionmodel;
import com.onoo.gomlgy.Models.data;
import com.onoo.gomlgy.Network.response.CategoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface getProductsWithSubcategory {
    @GET("get-category")
    Call<collectionmodel> get_Products_With_SubCategory(@Query("name") String subcategory);
}

package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.FiltersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FiltersInterface {

    @GET("some-data")
    Call<FiltersResponse> getFilters(@Query("category_id") String categoryId,
                                     @Query("sub_category_id") String subcategoryId);

}

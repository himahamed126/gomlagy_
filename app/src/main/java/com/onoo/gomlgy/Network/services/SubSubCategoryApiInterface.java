package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.SubCategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SubSubCategoryApiInterface {
    @GET
    Call<SubCategoryResponse> getSubSubcategories(@Url String url);
}

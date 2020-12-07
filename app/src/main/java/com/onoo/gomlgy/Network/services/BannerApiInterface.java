package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Network.response.BannerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BannerApiInterface {
    @GET("banners")
    Call<BannerResponse> getBanners();
}

package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.Models.offers_sources.offers.Offers;
import com.onoo.gomlgy.Network.response.SliderImageResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OffersApiInterface {
    @GET("offers")
    Call<Offers> getOffers();
}

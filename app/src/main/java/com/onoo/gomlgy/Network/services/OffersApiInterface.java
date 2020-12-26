package com.onoo.gomlgy.Network.services;

import com.onoo.gomlgy.models.offers_sources.offers.Offers;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OffersApiInterface {
    @GET("offers")
    Call<Offers> getOffers();
}

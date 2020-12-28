package com.onoo.gomlgy.Presentation.presenters;

import com.google.gson.JsonObject;
import com.onoo.gomlgy.Network.response.AppSettingsResponse;
import com.onoo.gomlgy.Network.response.AuctionBidResponse;
import com.onoo.gomlgy.Presentation.ui.fragments.HomeView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.AppSettingsInteractor;
import com.onoo.gomlgy.domain.interactors.AuctionBidInteractor;
import com.onoo.gomlgy.domain.interactors.AuctionProductInteractor;
import com.onoo.gomlgy.domain.interactors.BannerInteractor;
import com.onoo.gomlgy.domain.interactors.BestSellingInteractor;
import com.onoo.gomlgy.domain.interactors.BrandInteractor;
import com.onoo.gomlgy.domain.interactors.FeaturedProductInteractor;
import com.onoo.gomlgy.domain.interactors.FlashDealInteractor;
import com.onoo.gomlgy.domain.interactors.HomeCategoriesInteractor;
import com.onoo.gomlgy.domain.interactors.SliderInteractor;
import com.onoo.gomlgy.domain.interactors.TodaysDealInteractor;
import com.onoo.gomlgy.domain.interactors.TopCategoryInteractor;
import com.onoo.gomlgy.domain.interactors.impl.AppSettingsInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.AuctionBidInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.AuctionProductInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.BannerInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.BestSellingInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.BrandInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.FeaturedProductInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.FlashDealInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.HomeCategoriesInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.SliderInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.TodaysDealInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.TopCategoriesInteractorImpl;
import com.onoo.gomlgy.models.AuctionProduct;
import com.onoo.gomlgy.models.Banner;
import com.onoo.gomlgy.models.Brand;
import com.onoo.gomlgy.models.Category;
import com.onoo.gomlgy.models.FlashDeal;
import com.onoo.gomlgy.models.Product;
import com.onoo.gomlgy.models.offers_sources.offers.OffersData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.onoo.gomlgy.Utils.AppConfig.ASSET_URL;

public class HomePresenter extends AbstractPresenter implements AppSettingsInteractor.CallBack, SliderInteractor.CallBack, HomeCategoriesInteractor.CallBack, TodaysDealInteractor.CallBack, FlashDealInteractor.CallBack, BestSellingInteractor.CallBack, BannerInteractor.CallBack, FeaturedProductInteractor.CallBack, BrandInteractor.CallBack, TopCategoryInteractor.CallBack, AuctionProductInteractor.CallBack, AuctionBidInteractor.CallBack {
    private HomeView homeView;

    public HomePresenter(Executor executor, MainThread mainThread, HomeView homeView) {
        super(executor, mainThread);
        this.homeView = homeView;
    }

    public void getAppSettings() {
        new AppSettingsInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getSliderImages() {
        new SliderInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getHomeCategories() {
        new HomeCategoriesInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getTodaysDeal() {
        new TodaysDealInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getFlashDeal() {
        new FlashDealInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getBestSelling() {
        new BestSellingInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getBanners() {
        new BannerInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getFeaturedProducts() {
        new FeaturedProductInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getTopCategories() {
        new TopCategoriesInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getPopularbrands() {
        new BrandInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void getAuctionProducts() {
        new AuctionProductInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    public void submitBid(JsonObject jsonObject, String token) {
        new AuctionBidInteractorImpl(mExecutor, mMainThread, this, jsonObject, token).execute();
    }

    @Override
    public void onAppSettingsLoaded(AppSettingsResponse appSettingsResponse) {
        if (homeView != null) {
            homeView.onAppSettingsLoaded(appSettingsResponse);
        }
    }

    @Override
    public void onAppSettingsLoadedError() {

    }

    @Override
    public void onSliderDownloaded(List<OffersData> sliderImages) {
        if (homeView != null) {
            homeView.setSliderImages(sliderImages);
        }
    }

    @Override
    public void onSliderDownloadError() {

    }

    @Override
    public void onHomeCategoriesDownloaded(List<Category> categories) {
        if (homeView != null) {
            homeView.setHomeCategories(categories);
        }
    }

    @Override
    public void onHomeCategoriesDownloadError() {

    }

    @Override
    public void onTodaysDealProductDownloaded(List<Product> products) {
        if (homeView != null) {
            homeView.setTodaysDeal(products);
        }
    }

    @Override
    public void onTodaysDealProductDownloadError() {

    }

    @Override
    public void onBestSellingProductDownloaded(List<Product> products) {
        if (homeView != null) {
            homeView.setBestSelling(mapResponse(products));
        }
    }

    @Override
    public void onBestSellingProductDownloadError() {

    }

    @Override
    public void onFeaturedProductDownloaded(List<Product> response) {
        if (homeView != null) {
            homeView.setFeaturedProducts(mapResponse(response));
        }
    }

    @Override
    public void onFeaturedProductDownloadError() {

    }

    @Override
    public void onTopCategoriesDownloaded(List<Category> categories) {
        if (homeView != null) {
//            homeView.setTopCategories(categories);
        }
    }

    @Override
    public void onTopCategoriesDownloadError() {

    }

    @Override
    public void onAuctionProductDownloaded(List<AuctionProduct> auctionProducts) {
        if (homeView != null) {
            homeView.setAuctionProducts(auctionProducts);
        }
    }

    @Override
    public void onAuctionProductDownloadError() {

    }

    @Override
    public void onBannersDownloaded(List<Banner> banners) {
        if (homeView != null) {

        }
    }

    @Override
    public void onBannersDownloadError() {

    }

    @Override
    public void onBrandsDownloaded(List<Brand> brands) {
        if (homeView != null) {
            homeView.setPopularBrands(brands);
        }
    }

    @Override
    public void onBrandsDownloadError() {

    }

    @Override
    public void onBidSubmitted(AuctionBidResponse auctionBidResponse) {
        if (homeView != null) {
            homeView.onAuctionBidSubmitted(auctionBidResponse);
        }
    }

    @Override
    public void onBidSubmittedError() {

    }

    @Override
    public void onFlashDealProductDownloaded(FlashDeal flashDeal) {
        if (homeView != null) {

        }
    }

    @Override
    public void onFlashDealProductDownloadError() {

    }

    private List<Product> mapResponse(List<Product> response) {
        List<Product> products = new ArrayList<>();
        for (Product product : response) {

            List<Double> prices = new ArrayList<>();
            prices.add(product.getUnitPrice());
            prices.add(product.getUnitPrice2());
            prices.add(product.getUnitPrice3());

            Collections.sort(prices);
            product.setPrices(prices);
            product.setThumbnailImage(ASSET_URL + product.getThumbnailImage());
            products.add(product);
        }
        return products;
    }

}
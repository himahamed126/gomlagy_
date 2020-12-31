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
import com.onoo.gomlgy.domain.interactors.Cat1Interactor;
import com.onoo.gomlgy.domain.interactors.Cat2Interactor;
import com.onoo.gomlgy.domain.interactors.Cat3Interactor;
import com.onoo.gomlgy.domain.interactors.Cat4Interactor;
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
import com.onoo.gomlgy.domain.interactors.impl.Cat1InteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.Cat2InteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.Cat3InteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.Cat4InteractorImpl;
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

import java.util.List;

import static com.onoo.gomlgy.Utils.AppConfig.mapResponse;

public class HomePresenter extends AbstractPresenter implements AppSettingsInteractor.CallBack,
        SliderInteractor.CallBack, HomeCategoriesInteractor.CallBack, TodaysDealInteractor.CallBack,
        FlashDealInteractor.CallBack, BestSellingInteractor.CallBack, BannerInteractor.CallBack,
        FeaturedProductInteractor.CallBack, BrandInteractor.CallBack, TopCategoryInteractor.CallBack,
        AuctionProductInteractor.CallBack, AuctionBidInteractor.CallBack, Cat1Interactor.CallBack,
        Cat2Interactor.CallBack, Cat3Interactor.CallBack, Cat4Interactor.CallBack {

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

    public void getCat1() {
        new Cat1InteractorImpl(mExecutor, mMainThread, this, 23).execute();
    }

    public void getCat2() {
        new Cat2InteractorImpl(mExecutor, mMainThread, this, 21).execute();
    }

    public void getCat3() {
        new Cat3InteractorImpl(mExecutor, mMainThread, this, 22).execute();
    }

    public void getCat4() {
        new Cat4InteractorImpl(mExecutor, mMainThread, this, 14).execute();
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
            homeView.setTodaysDeal(mapResponse(products));
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

    @Override
    public void onCat1ProductDownloaded(List<Product> products) {
        if (homeView != null) {
            homeView.setCat1(products);
        }
    }

    @Override
    public void onCat1ProductDownloadError() {

    }

    @Override
    public void onCat2ProductDownloaded(List<Product> products) {
        if (homeView != null) {
            homeView.setCat2(products);
        }
    }

    @Override
    public void onCat2ProductDownloadError() {

    }

    @Override
    public void onCat3ProductDownloaded(List<Product> products) {
        if (homeView != null) {
            homeView.setCat3(products);
        }
    }

    @Override
    public void onCat3ProductDownloadError() {

    }

    @Override
    public void onCat4ProductDownloaded(List<Product> products) {
        if (homeView != null) {
            homeView.setCat4(products);
        }
    }

    @Override
    public void onCat4ProductDownloadError() {

    }
}
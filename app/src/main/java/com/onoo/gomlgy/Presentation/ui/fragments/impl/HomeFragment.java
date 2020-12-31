package com.onoo.gomlgy.Presentation.ui.fragments.impl;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.kingfisher.easyviewindicator.AnyViewIndicator;
import com.onoo.gomlgy.models.AuctionProduct;
import com.onoo.gomlgy.models.Brand;
import com.onoo.gomlgy.models.Category;
import com.onoo.gomlgy.models.Product;
import com.onoo.gomlgy.models.SubCategorymodel;
import com.onoo.gomlgy.models.offers_sources.offers.OffersData;
import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.AppSettingsResponse;
import com.onoo.gomlgy.Network.response.AuctionBidResponse;
import com.onoo.gomlgy.Network.response.AuthResponse;
import com.onoo.gomlgy.Network.response.ProductListingResponse;
import com.onoo.gomlgy.Network.services.ProductListingApiInterface;
import com.onoo.gomlgy.Presentation.presenters.HomePresenter;
import com.onoo.gomlgy.Presentation.ui.activities.impl.LoginActivity;
import com.onoo.gomlgy.Presentation.ui.activities.impl.ProductDetailsActivity;
import com.onoo.gomlgy.Presentation.ui.activities.impl.ProductListingActivity;
import com.onoo.gomlgy.Presentation.ui.adapters.AuctionProductAdapter;
import com.onoo.gomlgy.Presentation.ui.adapters.BestSellingAdapter;
import com.onoo.gomlgy.Presentation.ui.adapters.BrandAdapter;
import com.onoo.gomlgy.Presentation.ui.adapters.FeaturedProductAdapter;
import com.onoo.gomlgy.Presentation.ui.adapters.HomeSubCategoryAdapter;
import com.onoo.gomlgy.Presentation.ui.adapters.TodaysDealAdapter;
import com.onoo.gomlgy.Presentation.ui.fragments.HomeView;
import com.onoo.gomlgy.Presentation.ui.listeners.AuctionClickListener;
import com.onoo.gomlgy.Presentation.ui.listeners.BrandClickListener;
import com.onoo.gomlgy.Presentation.ui.listeners.CategoryClickListener;
import com.onoo.gomlgy.Presentation.ui.listeners.ProductClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.AppConfig;
import com.onoo.gomlgy.Utils.CustomToast;
import com.onoo.gomlgy.Utils.UserPrefs;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements HomeView, CategoryClickListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, ProductClickListener, BrandClickListener, AuctionClickListener {
    private View v;
    List<OffersData> sliderImages;
    private SliderLayout sliderLayout;
    private HomePresenter homePresenter;
    private AnyViewIndicator gridIndicator;
    private RelativeLayout auction_product_section, todays_deal_section;
    private AuthResponse authResponse;
    private ProgressDialog progressDialog;
    private BottomSheetDialog dialog;
    private RecyclerView auction_recyclerView;
    private List<AuctionProduct> mAuctionProducts = new ArrayList<>();
    private AuctionProductAdapter adapter;
    private CountdownView mCvCountdownView;
    private static final String TAG = "HomeFragment";
    private ProductListingApiInterface apiService1;


    TextView bestSellingSeeAll, cat1SeeAll, cat2SeeAll, cat3SeeAll, cat4SeeAll;
    HomeSubCategoryAdapter catAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, null);

        //imageSliderShadow = v.findViewById(R.id.imageSliderShadow);
        //gridIndicator = v.findViewById(R.id.anyViewIndicator);
        auction_product_section = v.findViewById(R.id.auction_product_section);
        todays_deal_section = v.findViewById(R.id.todays_deal_section);

        mCvCountdownView = (CountdownView) v.findViewById(R.id.countdown);

        auction_product_section.setVisibility(View.GONE);
        todays_deal_section.setVisibility(View.GONE);
        progressDialog = new ProgressDialog(getContext());
        auction_recyclerView = v.findViewById(R.id.auction_products);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false);
        auction_recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new AuctionProductAdapter(getActivity(), mAuctionProducts, this);
        auction_recyclerView.addItemDecoration(new LayoutMarginDecoration(1, AppConfig.convertDpToPx(getContext(), 10)));
        auction_recyclerView.setAdapter(adapter);


        sliderLayout = v.findViewById(R.id.imageSlider);
        sliderLayout.setVisibility(View.GONE);
//        sliderLayout.stopAutoCycle();

        homePresenter = new HomePresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        homePresenter.getAppSettings();
        homePresenter.getSliderImages();
        homePresenter.getTopCategories();
        homePresenter.getBanners();
        apiService1 = ApiClient.getClient().create(ProductListingApiInterface.class);

        return v;
    }

    @Override
    public void onAppSettingsLoaded(AppSettingsResponse appSettingsResponse) {
        UserPrefs userPrefs = new UserPrefs(getContext());
        userPrefs.setAppSettingsPreferenceObject(appSettingsResponse, "app_settings_response");

        homePresenter.getTodaysDeal();
        homePresenter.getFlashDeal();
        homePresenter.getBestSelling();
        homePresenter.getFeaturedProducts();
        homePresenter.getPopularbrands();
        homePresenter.getAuctionProducts();
        homePresenter.getCat1();
        homePresenter.getCat2();
        homePresenter.getCat3();
        homePresenter.getCat4();
    }

    @Override
    public void setSliderImages(List<OffersData> sliderImages) {
        this.sliderImages = sliderImages;
        for (OffersData offersData : sliderImages) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .description("")
                    .image(offersData.getOfferImagePath())
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayout.addSlider(textSliderView);
            //Log.d("Sliders", AppConfig.ASSET_URL + sliderImage.getPhoto());
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.addOnPageChangeListener(this);
        sliderLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setHomeCategories(List<Category> categories) {
//        RecyclerView recyclerView = v.findViewById(R.id.home_categories);
//        GridLayoutManager horizontalLayoutManager
//                = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(horizontalLayoutManager);
//        HomeCategoryAdapter adapter = new HomeCategoryAdapter(getActivity(), categories, HomeFragment.this);
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setTodaysDeal(List<Product> products) {
        List<Product> productList = new ArrayList<>();
        RecyclerView recyclerView = v.findViewById(R.id.todays_deals);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        for (int x = 0; x < products.size(); x++) {
            if (productList.size() < 4) {
                productList.add(products.get(x));
                TodaysDealAdapter adapter = new TodaysDealAdapter(getActivity(), productList, this);
                recyclerView.addItemDecoration(new LayoutMarginDecoration(2, AppConfig.convertDpToPx(getContext(), 4)));
                recyclerView.setAdapter(adapter);
            }
        }
        todays_deal_section.setVisibility(View.VISIBLE);
    }


    @Override
    public void setBestSelling(final List<Product> products) {
        List<Product> productList = new ArrayList<>();
        RecyclerView recyclerView = v.findViewById(R.id.best_selling);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        //recyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());
        for (int x = 0; x < products.size(); x++) {
            if (productList.size() < 4) {
                productList.add(products.get(x));
                BestSellingAdapter adapter = new BestSellingAdapter(getActivity(), productList, this);
                recyclerView.addItemDecoration(new LayoutMarginDecoration(1, AppConfig.convertDpToPx(getContext(), 3)));
                recyclerView.setAdapter(adapter);
            }
        }

    }

    void goToProductList(int subCategoryID) {
        int categoryId1 = 5;
        Log.i("URLLLL", "https://www.gomlgy.com/api/v1/get-product?category_id=" + categoryId1 + "&sub_category_id=" + subCategoryID);
        Intent i = new Intent(getActivity(), ProductListingActivity.class);
        i.putExtra("url", "https://www.gomlgy.com/api/v1/get-product?category_id=" + categoryId1 + "&sub_category_id=" + subCategoryID);
//                i.putExtra("title", model.getName());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(i);
    }

    @Override
    public void setFeaturedProducts(List<Product> products) {

        RecyclerView recyclerView = v.findViewById(R.id.featured_products);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        FeaturedProductAdapter adapter = new FeaturedProductAdapter(getActivity(), products, this);
        recyclerView.addItemDecoration(new LayoutMarginDecoration(1, AppConfig.convertDpToPx(getContext(), 10)));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void setCat1(List<Product> products) {
        RecyclerView recyclerView = v.findViewById(R.id.cat_1_deals);
        GridLayoutManager horizontalLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        catAdapter = new HomeSubCategoryAdapter(getActivity());
        recyclerView.addItemDecoration(new LayoutMarginDecoration(2, AppConfig.convertDpToPx(getContext(), 10)));
        recyclerView.setAdapter(catAdapter);
        cat1SeeAll = v.findViewById(R.id.cat_1_see_all);
        cat1SeeAll.setVisibility(View.VISIBLE);
        cat1SeeAll.setOnClickListener(v -> goToProductList(13));

        List<Product> productList = new ArrayList<>();

        for (int x = 0; x < products.size(); x++)
            if (productList.size() < 4) productList.add(products.get(x));

        catAdapter.setItems(productList);
    }

    @Override
    public void setCat2(List<Product> products) {
        RecyclerView recyclerView = v.findViewById(R.id.cat_2_deals);
        GridLayoutManager horizontalLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        catAdapter = new HomeSubCategoryAdapter(getActivity());
        recyclerView.addItemDecoration(new LayoutMarginDecoration(2, AppConfig.convertDpToPx(getContext(), 10)));
        recyclerView.setAdapter(catAdapter);
        cat2SeeAll = v.findViewById(R.id.cat_2_see_all);
        cat2SeeAll.setVisibility(View.VISIBLE);
        cat2SeeAll.setOnClickListener(v -> goToProductList(13));

        List<Product> productList = new ArrayList<>();

        for (int x = 0; x < products.size(); x++)
            if (productList.size() < 4) productList.add(products.get(x));
        catAdapter.setItems(productList);
    }

    @Override
    public void setCat3(List<Product> products) {
        RecyclerView recyclerView = v.findViewById(R.id.cat_3_deals);
        GridLayoutManager horizontalLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        catAdapter = new HomeSubCategoryAdapter(getActivity());
        recyclerView.addItemDecoration(new LayoutMarginDecoration(2, AppConfig.convertDpToPx(getContext(), 10)));
        recyclerView.setAdapter(catAdapter);
        cat3SeeAll = v.findViewById(R.id.cat_3_see_all);
        cat3SeeAll.setVisibility(View.VISIBLE);
        cat3SeeAll.setOnClickListener(v -> goToProductList(13));

        List<Product> productList = new ArrayList<>();

        for (int x = 0; x < products.size(); x++)
            if (productList.size() < 4) productList.add(products.get(x));

        catAdapter.setItems(productList);
    }

    @Override
    public void setCat4(List<Product> products) {
        RecyclerView recyclerView = v.findViewById(R.id.cat_4_deals);
        GridLayoutManager horizontalLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        catAdapter = new HomeSubCategoryAdapter(getActivity());
        recyclerView.addItemDecoration(new LayoutMarginDecoration(2, AppConfig.convertDpToPx(getContext(), 10)));
        recyclerView.setAdapter(catAdapter);
        cat4SeeAll = v.findViewById(R.id.cat_4_see_all);
        cat4SeeAll.setVisibility(View.VISIBLE);
        cat4SeeAll.setOnClickListener(v -> goToProductList(13));

        List<Product> productList = new ArrayList<>();

        for (int x = 0; x < products.size(); x++)
            if (productList.size() < 4) productList.add(products.get(x));

        catAdapter.setItems(productList);
    }

    @Override
    public void setPopularBrands(List<Brand> brands) {
        RecyclerView recyclerView = v.findViewById(R.id.popular_brads);
        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        BrandAdapter adapter = new BrandAdapter(getActivity(), brands, this);
        recyclerView.addItemDecoration(new LayoutMarginDecoration(2, AppConfig.convertDpToPx(getContext(), 10)));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setAuctionProducts(List<AuctionProduct> auctionProducts) {
        mAuctionProducts.clear();
        mAuctionProducts.addAll(auctionProducts);
        if (auctionProducts.size() > 0) {
            auction_product_section.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
        } else {
            auction_product_section.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        //Log.d("SliderPosition", String.valueOf(position));
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("SliderPosition", String.valueOf(position));
        //Glide.with(getContext()).load(AppConfig.ASSET_URL + sliderImages.get(position).getPhoto()).transform(new BlurTransformation(75, 1)).into(imageSliderShadow);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //Log.d("SliderPosition", String.valueOf(state));
    }

    @Override
    public void onCategoryItemClick(Category category) {
        Intent intent = new Intent(getContext(), ProductListingActivity.class);
        intent.putExtra("title", category.getName());
        intent.putExtra("url", category.getLinks().getProducts());
        startActivity(intent);
    }

    @Override
    public void onProductItemClick(Product product) {
        Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("link", product.getLinks().getDetails());
        intent.putExtra("top_selling", product.getLinks().getRelated());
        startActivity(intent);
    }

    @Override
    public void onBrandItemClick(Brand brand) {
        Intent intent = new Intent(getContext(), ProductListingActivity.class);
        intent.putExtra("title", brand.getName());
        intent.putExtra("url", brand.getLinks().getProducts());
        startActivity(intent);
    }

    @Override
    public void onAuctionItemClick(AuctionProduct auctionProduct) {
        View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog_auction, null);
        ImageView image = view.findViewById(R.id.product_image);
        TextView current_bid_amount = view.findViewById(R.id.current_bid_amount);
        TextView total_bids = view.findViewById(R.id.total_bids);
        TextView name = view.findViewById(R.id.product_name);
        EditText bid_amount = view.findViewById(R.id.bid_amount);
        Button place_bid = view.findViewById(R.id.place_bid);

        Glide.with(getContext()).load(AppConfig.ASSET_URL + auctionProduct.getImage()).into(image);
        current_bid_amount.setText(AppConfig.convertPrice(getContext(), auctionProduct.getCurrentPrice()));
        total_bids.setText(auctionProduct.getBidsCount() + getString(R.string._bids));
        name.setText(auctionProduct.getName());


        dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(view);
        dialog.show();

        place_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double amount = (bid_amount.getText().length() > 0) ? Double.valueOf(bid_amount.getText().toString()) : 0.0;
                if (amount > auctionProduct.getCurrentPrice()) {
                    authResponse = new UserPrefs(getActivity()).getAuthPreferenceObjectJson("auth_response");
                    if (authResponse != null && authResponse.getUser() != null) {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("auction_product_id", auctionProduct.getId());
                        jsonObject.addProperty("user_id", authResponse.getUser().getId());
                        jsonObject.addProperty("amount", amount);

                        dialog.hide();
                        progressDialog.setMessage(getString(R.string.your_bid_is_being_submitted_please_wait));
                        progressDialog.show();
                        homePresenter.submitBid(jsonObject, authResponse.getAccessToken());
                    } else {
                        startActivityForResult(new Intent(getActivity(), LoginActivity.class), 100);
                    }
                } else {
                    CustomToast.showToast(getActivity(), getString(R.string.your_bidding_amount_must_be_greater_than_the_current_bid), R.color.colorWarning);
                    bid_amount.requestFocus();
                }
            }
        });
    }

    @Override
    public void onAuctionBidSubmitted(AuctionBidResponse auctionBidResponse) {
        progressDialog.dismiss();
        CustomToast.showToast(getActivity(), auctionBidResponse.getMessage(), R.color.colorSuccess);
        homePresenter.getAuctionProducts();
    }
}
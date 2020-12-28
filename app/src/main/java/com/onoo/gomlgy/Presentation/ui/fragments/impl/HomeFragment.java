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
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
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
import com.onoo.gomlgy.Presentation.ui.adapters.ProductsAdapter;
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
import com.onoo.gomlgy.databinding.FragmentHomeBinding;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.onoo.gomlgy.models.AuctionProduct;
import com.onoo.gomlgy.models.Brand;
import com.onoo.gomlgy.models.Category;
import com.onoo.gomlgy.models.Product;
import com.onoo.gomlgy.models.offers_sources.offers.OffersData;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements HomeView, CategoryClickListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, ProductClickListener, BrandClickListener, AuctionClickListener {
    List<OffersData> sliderImages;
    //    private SliderLayout sliderLayout;
    private HomePresenter homePresenter;
    //    private AnyViewIndicator gridIndicator;
//    private RelativeLayout auction_product_section, todays_deal_section;
    private AuthResponse authResponse;
    private ProgressDialog progressDialog;
    private BottomSheetDialog dialog;
    //    private RecyclerView auction_recyclerView;
    private List<AuctionProduct> mAuctionProducts = new ArrayList<>();
    private AuctionProductAdapter adapter;
    //    private CountdownView mCvCountdownView;
    private static final String TAG = "HomeFragment";
    private ProductListingApiInterface apiService1;
    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container,
                false);

        binding.auctionProductSection.setVisibility(View.GONE);
        binding.todaysDealSection.setVisibility(View.GONE);
        progressDialog = new ProgressDialog(getContext());
        adapter = new AuctionProductAdapter(getActivity(), mAuctionProducts, this);
        binding.auctionProducts.addItemDecoration(
                new LayoutMarginDecoration(1,
                        AppConfig.convertDpToPx(getContext(), 10)));
        binding.auctionProducts.setAdapter(adapter);
        binding.imageSlider.setVisibility(View.GONE);
//        sliderLayout.stopAutoCycle();

        homePresenter = new HomePresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        homePresenter.getAppSettings();
        homePresenter.getSliderImages();
        homePresenter.getTopCategories();
        homePresenter.getBanners();
        apiService1 = ApiClient.getClient().create(ProductListingApiInterface.class);

        getData1(23);
        getData2(21);
        getData3(22);
        getData4(14);

        return binding.getRoot();
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
            binding.imageSlider.addSlider(textSliderView);
            //Log.d("Sliders", AppConfig.ASSET_URL + sliderImage.getPhoto());
        }
        binding.imageSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        binding.imageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        binding.imageSlider.addOnPageChangeListener(this);
        binding.imageSlider.setVisibility(View.VISIBLE);
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
        for (int x = 0; x < products.size(); x++) {
            if (productList.size() < 4) {
                productList.add(products.get(x));
                TodaysDealAdapter adapter = new TodaysDealAdapter(getActivity(), productList,
                        this);
                binding.todaysDeals.addItemDecoration(new
                        LayoutMarginDecoration(2,
                        AppConfig.convertDpToPx(getContext(), 4)));
                binding.todaysDeals.setAdapter(adapter);
            }
        }
        binding.todaysDealSection.setVisibility(View.VISIBLE);
    }


    @Override
    public void setBestSelling(final List<Product> products) {
        List<Product> productList = new ArrayList<>();
        //recyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());
        for (int x = 0; x < products.size(); x++) {
            if (productList.size() < 4) {
                productList.add(products.get(x));
                BestSellingAdapter adapter = new BestSellingAdapter(getActivity(), productList,
                        this);
                binding.bestSelling.addItemDecoration(new
                        LayoutMarginDecoration(1,
                        AppConfig.convertDpToPx(getContext(), 3)));
                binding.bestSelling.setAdapter(adapter);
            }
        }

//        bestSellingSeeAll = v.findViewById(R.id.best_selling_see_all);
//        bestSellingSeeAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int categoryId = 1;
//                int subCategoryID = model.getId();
//                Log.i("URLLLL", "https://www.gomlgy.com/api/v1/get-product?category_id=" + categoryId + "&sub_category_id=" + subCategoryID);
//                Intent i = new Intent(getApplicationContext(), ProductListingActivity.class);
//                i.putExtra("url", "https://www.gomlgy.com/api/v1/get-product?category_id=" + categoryId + "&sub_category_id=" + subCategoryID);
//                i.putExtra("title", model.getName());
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                MainActivity.this.startActivity(i);
//            }
//        });
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

    private void getData1(int subCategoryID) {
        List<Product> productList = new ArrayList<>();
        String url = "https://www.gomlgy.com/api/v1/get-product?category_id=" + 5
                + "&sub_category_id=" + subCategoryID;
        Call<ProductListingResponse> call = apiService1.getProducts(url);
        call.enqueue(new Callback<ProductListingResponse>() {
            @Override
            public void onResponse(Call<ProductListingResponse> call,
                                   Response<ProductListingResponse> response) {
                try {
                    for (int x = 0; x < response.body().getData().size(); x++) {
                        if (productList.size() < 4) {
                            productList.add(response.body().getData().get(x));
                            HomeSubCategoryAdapter adapter = new
                                    HomeSubCategoryAdapter(getActivity(), productList);
                            binding.cat1Deals.addItemDecoration(new
                                    LayoutMarginDecoration(2,
                                    AppConfig.convertDpToPx(getContext(), 10)));
                            binding.cat1Deals.setAdapter(adapter);
                        }
                    }
                    binding.cat1SeeAll.setVisibility(View.VISIBLE);
                    binding.cat1SeeAll.setOnClickListener(v -> goToProductList(subCategoryID));
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductListingResponse> call, Throwable t) {
                Log.e("Exception", t.getMessage());
            }
        });
    }

    private void getData2(int subCategoryID) {
        List<Product> productList = new ArrayList<>();
        String url = "https://www.gomlgy.com/api/v1/get-product?category_id=" + 5
                + "&sub_category_id=" + subCategoryID;
        Call<ProductListingResponse> call = apiService1.getProducts(url);
        call.enqueue(new Callback<ProductListingResponse>() {
            @Override
            public void onResponse(Call<ProductListingResponse> call,
                                   Response<ProductListingResponse> response) {
                try {
                    for (int x = 0; x < response.body().getData().size(); x++) {
                        if (productList.size() < 4) {
                            productList.add(response.body().getData().get(x));
                            HomeSubCategoryAdapter adapter = new
                                    HomeSubCategoryAdapter(getActivity(), productList);
                            binding.cat2Deals.addItemDecoration(new
                                    LayoutMarginDecoration(2,
                                    AppConfig.convertDpToPx(getContext(), 10)));
                            binding.cat2Deals.setAdapter(adapter);
                        }
                    }
                    binding.cat2SeeAll.setVisibility(View.VISIBLE);
                    binding.cat2SeeAll.setOnClickListener(v -> goToProductList(subCategoryID));
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductListingResponse> call, Throwable t) {
                Log.e("Exception", t.getMessage());
            }
        });
    }

    private void getData3(int subCategoryID) {
        List<Product> productList = new ArrayList<>();
        String url = "https://www.gomlgy.com/api/v1/get-product?category_id=" + 5
                + "&sub_category_id=" + subCategoryID;
        Call<ProductListingResponse> call = apiService1.getProducts(url);
        call.enqueue(new Callback<ProductListingResponse>() {
            @Override
            public void onResponse(Call<ProductListingResponse> call,
                                   Response<ProductListingResponse> response) {
                try {
                    for (int x = 0; x < response.body().getData().size(); x++) {
                        if (productList.size() < 4) {
                            productList.add(response.body().getData().get(x));
                            HomeSubCategoryAdapter adapter = new
                                    HomeSubCategoryAdapter(getActivity(), productList);
                            binding.cat3Deals.addItemDecoration(new
                                    LayoutMarginDecoration(2,
                                    AppConfig.convertDpToPx(getContext(), 10)));
                            binding.cat3Deals.setAdapter(adapter);
                        }
                    }
                    binding.cat3SeeAll.setVisibility(View.VISIBLE);
                    binding.cat3SeeAll.setOnClickListener(v -> goToProductList(subCategoryID));
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductListingResponse> call, Throwable t) {
                Log.e("Exception", t.getMessage());
            }
        });
    }

    private void getData4(int subCategoryID) {
        List<Product> productList = new ArrayList<>();
        String url = "https://www.gomlgy.com/api/v1/get-product?category_id=" + 5
                + "&sub_category_id=" + subCategoryID;
        Call<ProductListingResponse> call = apiService1.getProducts(url);
        call.enqueue(new Callback<ProductListingResponse>() {
            @Override
            public void onResponse(Call<ProductListingResponse> call,
                                   Response<ProductListingResponse> response) {
                try {
                    for (int x = 0; x < response.body().getData().size(); x++) {
                        if (productList.size() < 4) {
                            productList.add(response.body().getData().get(x));
                            HomeSubCategoryAdapter adapter = new
                                    HomeSubCategoryAdapter(getActivity(), productList);
                            binding.cat4Deals.addItemDecoration(new
                                    LayoutMarginDecoration(2,
                                    AppConfig.convertDpToPx(getContext(), 10)));
                            binding.cat4Deals.setAdapter(adapter);
                        }
                    }
                    binding.cat4SeeAll.setVisibility(View.VISIBLE);
                    binding.cat4SeeAll.setOnClickListener(v -> goToProductList(subCategoryID));
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductListingResponse> call, Throwable t) {
                Log.e("Exception", t.getMessage());
            }
        });

    }

    @Override
    public void setFeaturedProducts(List<Product> products) {

        ProductsAdapter adapter = new ProductsAdapter(products,
                this);
        binding.featuredProducts.addItemDecoration(new LayoutMarginDecoration(1,
                AppConfig.convertDpToPx(getContext(), 10)));
        binding.featuredProducts.setAdapter(adapter);

    }

//    @Override
//    public void setTopCategories(List<Category> categories) {
//        RecyclerView recyclerView = v.findViewById(R.id.top_categories);
//        GridLayoutManager horizontalLayoutManager
//                = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(horizontalLayoutManager);
//        TopCategoryAdapter adapter = new TopCategoryAdapter(getActivity(), categories, HomeFragment.this);
//        recyclerView.addItemDecoration(new LayoutMarginDecoration(1, AppConfig.convertDpToPx(getContext(), 10)));
//        recyclerView.setAdapter(adapter);
//    }

    @Override
    public void setPopularBrands(List<Brand> brands) {
        BrandAdapter adapter = new BrandAdapter(getActivity(), brands, this);
        binding.popularBrads.addItemDecoration(new LayoutMarginDecoration(2,
                AppConfig.convertDpToPx(getContext(), 10)));
        binding.popularBrads.setAdapter(adapter);
    }

    @Override
    public void setAuctionProducts(List<AuctionProduct> auctionProducts) {
        mAuctionProducts.clear();
        mAuctionProducts.addAll(auctionProducts);
        if (auctionProducts.size() > 0) {
            binding.auctionProductSection.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
        } else {
            binding.auctionProductSection.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
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
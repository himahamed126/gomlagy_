package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.slider.RangeSlider;
import com.onoo.gomlgy.Network.response.ProductListingResponse;
import com.onoo.gomlgy.Presentation.presenters.FiltersPresenter;
import com.onoo.gomlgy.Presentation.presenters.ProductListingPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.FiltersView;
import com.onoo.gomlgy.Presentation.ui.activities.ProductListingView;
import com.onoo.gomlgy.Presentation.ui.adapters.FilterBrandsAdapter;
import com.onoo.gomlgy.Presentation.ui.adapters.ProductListingAdapter;
import com.onoo.gomlgy.Presentation.ui.listeners.EndlessRecyclerOnScrollListener;
import com.onoo.gomlgy.Presentation.ui.listeners.FilterBrandCallback;
import com.onoo.gomlgy.Presentation.ui.listeners.ProductClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.RecyclerViewMargin;
import com.onoo.gomlgy.databinding.ActivityProductListingBinding;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.onoo.gomlgy.models.BrandData;
import com.onoo.gomlgy.models.FilterData;
import com.onoo.gomlgy.models.Product;
import com.onoo.gomlgy.models.offers_sources.offers.OffersData;

import java.util.ArrayList;
import java.util.List;

public class ProductListingActivity extends BaseActivity implements ProductListingView,
        ProductClickListener, FiltersView, FilterBrandCallback {

    private List<Product> mProducts = new ArrayList<>();
    private ProductListingResponse productListingResponse = null;
    private ProductListingPresenter productListingPresenter;
    private ProductListingAdapter adapter;
    private List<OffersData> sliderImages;
    private ActivityProductListingBinding productListingBinding;
    private String url, categoryId, subcategoryId;
    private BottomSheetBehavior bottomSheetBehavior;
    private FiltersPresenter filtersPresenter;
    private List<BrandData> brandsList = new ArrayList<>();
    private FilterBrandsAdapter brandsAdapter;
    private int selectedFilterBrandId;
    private Boolean isBrandSelected = false;
    private FilterData filterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productListingBinding = DataBindingUtil.setContentView(ProductListingActivity.this,
                R.layout.activity_product_listing);

        initView();
        productListingPresenter = new ProductListingPresenter(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(), this);
        filtersPresenter = new FiltersPresenter(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(), this);
        getIntentExtras();
        productListingPresenter.getSliderImages();

    }

    @Override
    public void initView() {

        initializeActionBar();

        adapter = new ProductListingAdapter(getApplicationContext(), mProducts,
                this, ProductListingAdapter.ViewType.VIEW_TYPE_List);

        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(ProductListingActivity.this, 2);
        productListingBinding.productList.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(convertDpToPx(this, 10),
                2);
        productListingBinding.productList.addItemDecoration(decoration);
        productListingBinding.productList.setAdapter(adapter);

        productListingBinding.productList.addOnScrollListener(
                new EndlessRecyclerOnScrollListener() {
                    @Override
                    public void onLoadMore() {
                        addDataToList(productListingResponse);
                    }
                });
        productListingBinding.itemProgressBar.setVisibility(View.VISIBLE);

        initBottomSheet();

        productListingBinding.filterTv.setOnClickListener(view -> {

            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

            if (filterData != null) {
                productListingBinding.include.fromEt.setText(String.valueOf(filterData.getMinPrice()));
                productListingBinding.include.toEt.setText(String.valueOf(filterData.getMaxPrice()));

                productListingBinding.include.slider.setValueFrom(filterData.getMinPrice());
                productListingBinding.include.slider.setValueTo(filterData.getMaxPrice());
                productListingBinding.include.slider.setValues(Float.valueOf(filterData.getMinPrice()),
                        Float.valueOf(filterData.getMaxPrice()));
            }

        });

    }

    private void initBottomSheet() {

        bottomSheetBehavior = BottomSheetBehavior.from(productListingBinding.include.filtersSheetCl);

        brandsAdapter = new FilterBrandsAdapter(brandsList, this);
        productListingBinding.include.brandsRv.setAdapter(brandsAdapter);

        productListingBinding.include.expandBrandsIv.setOnClickListener(view -> {
            productListingBinding.include.brandsRv.setVisibility(View.VISIBLE);
            productListingBinding.include.expandBrandsIv.setVisibility(View.GONE);
            productListingBinding.include.minimizeBrandsIv.setVisibility(View.VISIBLE);
        });

        productListingBinding.include.minimizeBrandsIv.setOnClickListener(view -> {
            productListingBinding.include.brandsRv.setVisibility(View.GONE);
            productListingBinding.include.expandBrandsIv.setVisibility(View.VISIBLE);
            productListingBinding.include.minimizeBrandsIv.setVisibility(View.GONE);
        });

//        Change edit texts on slider values changed
        productListingBinding.include.slider.addOnSliderTouchListener(new RangeSlider
                .OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull RangeSlider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull RangeSlider slider) {
                productListingBinding.include.fromEt.setText(String
                        .valueOf(Math.round(slider.getValues().get(0))));
                productListingBinding.include.toEt.setText(String
                        .valueOf(Math.round(slider.getValues().get(1))));
            }
        });

    }

    @Override
    public void getIntentExtras() {

        if (getIntent().getStringExtra(getString(R.string.title)) != null)
            setTitle(getIntent().getStringExtra(getString(R.string.title)));

        if (getIntent().getStringExtra(getString(R.string.url)) != null) {
            url = getIntent().getStringExtra(getString(R.string.url));
            productListingPresenter.getProducts(url);
        }

        if (getIntent().getStringExtra(getString(R.string.category_id)) != null)
            categoryId = getIntent().getStringExtra(getString(R.string.category_id));

        if (getIntent().getStringExtra(getString(R.string.sub_category_id)) != null)
            subcategoryId = getIntent().getStringExtra(getString(R.string.category_id));

        filtersPresenter.getFilterData(categoryId, "13");

    }

    @Override
    public void setProducts(ProductListingResponse productListingResponse) {
        mProducts.addAll(productListingResponse.getData());
        this.productListingResponse = productListingResponse;
        productListingBinding.itemProgressBar.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();

        if (mProducts.size() <= 0) {
            productListingBinding.productsEmptyText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setSliderImages(List<OffersData> sliderImages) {
        this.sliderImages = sliderImages;
        for (OffersData offersData : sliderImages) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description("")
                    .image(offersData.getOfferImagePath())
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            productListingBinding.imageSlider.addSlider(textSliderView);
            //Log.d("Sliders", AppConfig.ASSET_URL + sliderImage.getPhoto());
        }
        productListingBinding.imageSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        productListingBinding.imageSlider
                .setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        productListingBinding.imageSlider.addOnPageChangeListener(
                new ViewPagerEx.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset,
                                               int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
        productListingBinding.imageSlider.setVisibility(View.VISIBLE);
    }

    public void addDataToList(ProductListingResponse productListingResponse) {
        if (productListingResponse != null && productListingResponse.getMeta() != null &&
                !productListingResponse.getMeta().getCurrentPage()
                        .equals(productListingResponse.getMeta().getLastPage())) {
            productListingBinding.itemProgressBar.setVisibility(View.VISIBLE);
            productListingPresenter.getProducts(productListingResponse.getLinks().getNext());
        }
    }

    @Override
    public void onProductItemClick(Product product) {
        // Toast.makeText(this,  product.getLinks().getDetails(), Toast.LENGTH_SHORT).show();
        Log.i("jjjjjjjjjjjj", "onProductItemClick: " + product.getLinks().getDetails());
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("link", product.getLinks().getDetails());
        intent.putExtra("top_selling", product.getLinks().getRelated());
        startActivity(intent);
    }

    public int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void setFilters(FilterData filterData) {
        this.filterData = filterData;
        brandsList.clear();
        brandsList.addAll(filterData.getBrands());
        brandsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBrandSelected(int position) {

//        Determine whether the user select a brand to filter with previously or not
        if (!isBrandSelected)
            isBrandSelected = true;
//        Set the previous selected brand to not selected
        brandsList.get(selectedFilterBrandId).setSelected(false);
        selectedFilterBrandId = position;
//        Set the current selected brand to selected
        brandsList.get(selectedFilterBrandId).setSelected(true);

        brandsAdapter.notifyDataSetChanged();

    }
}

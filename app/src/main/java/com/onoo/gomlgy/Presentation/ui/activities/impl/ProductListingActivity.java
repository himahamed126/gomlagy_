package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.onoo.gomlgy.Models.Product;
import com.onoo.gomlgy.Models.SliderImage;
import com.onoo.gomlgy.Models.offers_sources.offers.OffersData;
import com.onoo.gomlgy.Network.response.ProductListingResponse;
import com.onoo.gomlgy.Presentation.presenters.ProductListingPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.ProductListingView;
import com.onoo.gomlgy.Presentation.ui.adapters.ProductListingAdapter;
import com.onoo.gomlgy.Presentation.ui.listeners.EndlessRecyclerOnScrollListener;
import com.onoo.gomlgy.Presentation.ui.listeners.ProductClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.RecyclerViewMargin;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;

public class ProductListingActivity extends BaseActivity implements ProductListingView, ProductClickListener, ViewPagerEx.OnPageChangeListener {

    private List<Product> mProducts = new ArrayList<>();
    private ProductListingResponse productListingResponse = null;
    private ProductListingPresenter productListingPresenter;
    private ProductListingAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView products_empty_text;
    private SliderLayout sliderLayout;
    private ImageView orint;
    private static final String TAG = "ProductListingActivity";
    List<OffersData> sliderImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);

        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");

        Log.i(TAG, "onCreate: " + url);
        initializeActionBar();
        setTitle(title);

        adapter = new ProductListingAdapter(getApplicationContext(), mProducts, this, ProductListingAdapter.ViewType.VIEW_TYPE_List);
        recyclerView = findViewById(R.id.product_list);
        progressBar = findViewById(R.id.item_progress_bar);
        products_empty_text = findViewById(R.id.products_empty_text);
        sliderLayout = findViewById(R.id.imageSlider);

        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(ProductListingActivity.this, 2);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(convertDpToPx(this, 10), 2);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList(productListingResponse);
            }
        });
        progressBar.setVisibility(View.VISIBLE);
        productListingPresenter = new ProductListingPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        productListingPresenter.getProducts(url);
        productListingPresenter.getSliderImages();
    }

    @Override
    public void setProducts(ProductListingResponse productListingResponse) {
        mProducts.addAll(productListingResponse.getData());
        this.productListingResponse = productListingResponse;
        progressBar.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();

        if (mProducts.size() <= 0) {
            products_empty_text.setVisibility(View.VISIBLE);
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
            sliderLayout.addSlider(textSliderView);
            //Log.d("Sliders", AppConfig.ASSET_URL + sliderImage.getPhoto());
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.addOnPageChangeListener(this);
        sliderLayout.setVisibility(View.VISIBLE);
    }

    public void addDataToList(ProductListingResponse productListingResponse) {
        if (productListingResponse != null && productListingResponse.getMeta() != null && !productListingResponse.getMeta().getCurrentPage().equals(productListingResponse.getMeta().getLastPage())) {
            progressBar.setVisibility(View.VISIBLE);
            productListingPresenter.getProducts(productListingResponse.getLinks().getNext().toString());
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

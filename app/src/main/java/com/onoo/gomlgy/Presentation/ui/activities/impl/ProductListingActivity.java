package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.Models.Product;
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

public class ProductListingActivity extends BaseActivity implements ProductListingView, ProductClickListener {

    private List<Product> mProducts = new ArrayList<>();
    private ProductListingResponse productListingResponse = null;
    private ProductListingPresenter productListingPresenter;
    private ProductListingAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView products_empty_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);

        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");

        initializeActionBar();
        setTitle(title);

        adapter = new ProductListingAdapter(getApplicationContext(), mProducts, this);
        recyclerView = findViewById(R.id.product_list);
        progressBar = findViewById(R.id.item_progress_bar);
        products_empty_text = findViewById(R.id.products_empty_text);

        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(ProductListingActivity.this, 2);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        //adapter.setClickListener(this);
        RecyclerViewMargin decoration = new RecyclerViewMargin(convertDpToPx(this,10), 2);
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
    }

    @Override
    public void setProducts(ProductListingResponse productListingResponse) {
        mProducts.addAll(productListingResponse.getData());
        this.productListingResponse = productListingResponse;
        progressBar.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();

        if (mProducts.size() <= 0){
            products_empty_text.setVisibility(View.VISIBLE);
        }
    }

    public void addDataToList(ProductListingResponse productListingResponse){
        if (productListingResponse != null && productListingResponse.getMeta() != null && !productListingResponse.getMeta().getCurrentPage().equals(productListingResponse.getMeta().getLastPage())){
            progressBar.setVisibility(View.VISIBLE);
            productListingPresenter.getProducts(productListingResponse.getLinks().getNext().toString());
        }
    }

    @Override
    public void onProductItemClick(Product product) {
       // Toast.makeText(this,  product.getLinks().getDetails(), Toast.LENGTH_SHORT).show();
        Log.i("jjjjjjjjjjjj", "onProductItemClick: "+ product.getLinks().getDetails());
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("link", product.getLinks().getDetails());
        intent.putExtra("top_selling", product.getLinks().getRelated());
        startActivity(intent);
    }
    public int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}

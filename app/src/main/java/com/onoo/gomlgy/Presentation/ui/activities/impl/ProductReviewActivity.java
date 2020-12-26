package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.models.Review;
import com.onoo.gomlgy.Presentation.presenters.ProductReviewPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.ProductReviewView;
import com.onoo.gomlgy.Presentation.ui.adapters.ProductReviewAdapter;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;

import java.util.List;

public class ProductReviewActivity extends BaseActivity implements ProductReviewView {
    private String url;
    private ProgressBar progressBar;
    private TextView product_reviews_empty_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_review);

        url = getIntent().getStringExtra("url");

        initializeActionBar();
        setTitle(getString(R.string.product_reviews));

        progressBar = findViewById(R.id.item_progress_bar);
        product_reviews_empty_text = findViewById(R.id.product_reviews_empty_text);

        progressBar.setVisibility(View.VISIBLE);
        new ProductReviewPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).sendUpdateProfileRequest(url);
    }

    @Override
    public void onReviewsLoaded(List<Review> reviews) {
        progressBar.setVisibility(View.GONE);
        if (reviews.size() > 0){
            RecyclerView recyclerView = findViewById(R.id.product_reviews);
            LinearLayoutManager horizontalLayoutManager
                    = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(horizontalLayoutManager);
            ProductReviewAdapter adapter = new ProductReviewAdapter(this, reviews);
            recyclerView.setAdapter(adapter);
        }
        else {
            product_reviews_empty_text.setVisibility(View.VISIBLE);
        }
    }
}

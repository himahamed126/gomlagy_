package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.Presentation.ui.adapters.ProductsAdapter;
import com.onoo.gomlgy.Presentation.ui.listeners.ProductClickListener;
import com.onoo.gomlgy.models.Product;
import com.onoo.gomlgy.models.WishlistModel;
import com.onoo.gomlgy.models.WishlistProduct;
import com.onoo.gomlgy.Network.response.AuthResponse;
import com.onoo.gomlgy.Presentation.presenters.WishlistPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.WishlistView;
import com.onoo.gomlgy.Presentation.ui.adapters.WishlistAdapter;
import com.onoo.gomlgy.Presentation.ui.listeners.WishlistProductClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.AppConfig;
import com.onoo.gomlgy.Utils.RecyclerViewMargin;
import com.onoo.gomlgy.Utils.UserPrefs;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;

import java.util.List;

import static com.onoo.gomlgy.Utils.AppConfig.mapResponse;

public class WishlistActivity extends BaseActivity implements WishlistView, WishlistProductClickListener {
    private AuthResponse authResponse;
    private WishlistPresenter wishlistPresenter;
    private ProgressBar progressBar;
    private TextView wishlist_empty_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        initializeActionBar();
        setTitle(getString(R.string.my_wishlist));

        progressBar = findViewById(R.id.item_progress_bar);
        wishlist_empty_text = findViewById(R.id.wishlist_empty_text);

        wishlistPresenter = new WishlistPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);

        authResponse = new UserPrefs(getApplicationContext()).getAuthPreferenceObjectJson("auth_response");
        if (authResponse != null && authResponse.getUser() != null) {
            progressBar.setVisibility(View.VISIBLE);
            wishlistPresenter.getWishlistItems(authResponse.getUser().getId(), authResponse.getAccessToken());
        } else {
            //startActivityForResult(new Intent(getActivity(), LoginActivity.class), 100);
            //getActivity().finish();
        }
    }

    @Override
    public void setWishlistProducts(List<WishlistModel> wishlistModels) {
        progressBar.setVisibility(View.GONE);
        if (wishlistModels.size() > 0) {
            WishlistAdapter adapter = new WishlistAdapter(getApplicationContext(), wishlistModels, this);
            RecyclerView recyclerView = findViewById(R.id.product_list);
            LinearLayoutManager horizontalLayoutManager
                    = new LinearLayoutManager(WishlistActivity.this, RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(horizontalLayoutManager);
            RecyclerViewMargin decoration = new RecyclerViewMargin(AppConfig.convertDpToPx(this, 10), 1);
            recyclerView.addItemDecoration(decoration);
            recyclerView.setAdapter(adapter);
        } else {
            wishlist_empty_text.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onProductItemClick(WishlistProduct product) {
        Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("link", product.getLinks().getDetails());
        intent.putExtra("top_selling", product.getLinks().getRelated());
        startActivity(intent);
    }

}

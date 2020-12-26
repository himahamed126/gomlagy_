package com.onoo.gomlgy.Presentation.ui.fragments.impl;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.models.SearchProduct;
import com.onoo.gomlgy.Network.response.ProductSearchResponse;
import com.onoo.gomlgy.Presentation.presenters.ProductSearchPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.impl.ProductDetailsActivity;
import com.onoo.gomlgy.Presentation.ui.adapters.ProductSearchAdapter;
import com.onoo.gomlgy.Presentation.ui.fragments.ProductSearchView;
import com.onoo.gomlgy.Presentation.ui.listeners.EndlessRecyclerOnScrollListener;
import com.onoo.gomlgy.Presentation.ui.listeners.SearchProductClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.AppConfig;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.ArrayList;
import java.util.List;

public class ProductSearchFragment extends Fragment implements ProductSearchView, SearchProductClickListener {
    private View v;

    private List<SearchProduct> mProducts = new ArrayList<>();
    private SearchView searchView;
    private RadioGroup radioScope;
    private ProductSearchAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ProductSearchPresenter productSearchPresenter;
    private String url = AppConfig.BASE_URL + "products/search?key=&scope=product&page=1";
    private String key = "", scope = "product";
    private ProductSearchResponse mProductSearchResponse = null;
    EditText searchEt;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_search, null);

        recyclerView = v.findViewById(R.id.product_list);

        recyclerView.addItemDecoration(new LayoutMarginDecoration(1, AppConfig.convertDpToPx(getContext(), 10)));

        progressBar = v.findViewById(R.id.item_progress_bar);

        //radioScope = v.findViewById(R.id.scope_radio);

        productSearchPresenter = new ProductSearchPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);

        searchEt = getActivity().findViewById(R.id.search_et);
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchProduct(s.toString(), "product");
            }
        });

        searchProduct("", "product");

        return v;
    }

    private void searchProduct(String key, String scope) {
        //Log.d("Test", scope);
        url = url.replace("key=" + url.split("key=")[1].split("&")[0], "key=" + key);
        url = url.replace("scope=" + url.split("scope=")[1].split("&")[0], "scope=" + scope.toLowerCase());

        //Log.d("Test", url);

        mProducts.removeAll(mProducts);

        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(horizontalLayoutManager);

        adapter = new ProductSearchAdapter(getContext(), mProducts, this);

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList(mProductSearchResponse);
            }
        });

        recyclerView.setAdapter(adapter);

        progressBar.setVisibility(View.VISIBLE);

        productSearchPresenter.getSearchedProducts(url);
    }

    public void addDataToList(ProductSearchResponse productSearchResponse) {
        if (productSearchResponse != null && productSearchResponse.getMeta() != null && !productSearchResponse.getMeta().getCurrentPage().equals(productSearchResponse.getMeta().getLastPage())) {
            progressBar.setVisibility(View.VISIBLE);
            productSearchPresenter.getSearchedProducts(productSearchResponse.getLinks().getNext());
        }
    }

    @Override
    public void onProductItemClick(SearchProduct product) {
        Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("link", product.getLinks().getDetails());
        intent.putExtra("top_selling", product.getLinks().getRelated());
        startActivity(intent);
    }

    @Override
    public void setSearchedProduct(ProductSearchResponse productSearchResponse) {
        Log.d("Test", String.valueOf(productSearchResponse.getMeta().getTotal()));
        mProducts.addAll(productSearchResponse.getData());
        mProductSearchResponse = productSearchResponse;
        progressBar.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }
}

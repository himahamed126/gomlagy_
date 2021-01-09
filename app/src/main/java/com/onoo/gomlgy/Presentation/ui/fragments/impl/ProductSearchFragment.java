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

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.onoo.gomlgy.Network.response.ProductSearchResponse;
import com.onoo.gomlgy.Presentation.presenters.ProductSearchPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.impl.ProductDetailsActivity;
import com.onoo.gomlgy.Presentation.ui.adapters.ProductsAdapter;
import com.onoo.gomlgy.Presentation.ui.fragments.ProductSearchView;
import com.onoo.gomlgy.Presentation.ui.listeners.EndlessRecyclerOnScrollListener;
import com.onoo.gomlgy.Presentation.ui.listeners.ProductClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.AppConfig;
import com.onoo.gomlgy.databinding.FragmentSearchBinding;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.onoo.gomlgy.models.Product;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.ArrayList;
import java.util.List;

import static com.onoo.gomlgy.Utils.AppConfig.mapResponse;

public class ProductSearchFragment extends Fragment implements ProductSearchView, ProductClickListener {
    private FragmentSearchBinding binding;

    private List<Product> mProducts = new ArrayList<>();
    private ProductsAdapter adapter;
    private ProductSearchPresenter productSearchPresenter;
    private String url = AppConfig.BASE_URL + "products/search?key=&scope=product&page=1";
    private String key = "", scope = "product";
    private ProductSearchResponse mProductSearchResponse = null;
    private EditText searchEt;
    private static final String TAG = "SearchFragment";

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container,
                false);

        initView();

        productSearchPresenter = new ProductSearchPresenter(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(), this);

        searchProduct("", "product");

        return binding.getRoot();
    }

    private void initView() {
        binding.productList.addItemDecoration(new LayoutMarginDecoration(1,
                AppConfig.convertDpToPx(getContext(), 10)));

        adapter = new ProductsAdapter(getActivity(), mProducts, this);
        adapter.setViewType(2);
        binding.productList.setAdapter(adapter);
        binding.productList.setLayoutManager(new GridLayoutManager(getActivity(), 1));

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
                binding.itemProgressBar.setVisibility(View.VISIBLE);
                searchProduct(s.toString(), "product");
            }
        });

        binding.productList.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList(mProductSearchResponse);
            }
        });
    }

    private void searchProduct(String key, String scope) {
        mProducts.clear();
        url = url.replace("key=" + url.split("key=")[1].split("&")[0], "key=" + key);
        url = url.replace("scope=" + url.split("scope=")[1].split("&")[0], "scope=" + scope.toLowerCase());
        productSearchPresenter.getSearchedProducts(url);
    }

    public void addDataToList(ProductSearchResponse productSearchResponse) {
        if (productSearchResponse != null && productSearchResponse.getMeta() != null && !productSearchResponse.getMeta().getCurrentPage().equals(productSearchResponse.getMeta().getLastPage())) {
            binding.itemProgressBar.setVisibility(View.VISIBLE);
            productSearchPresenter.getSearchedProducts(productSearchResponse.getLinks().getNext());
        }
    }

    @Override
    public void setSearchedProduct(ProductSearchResponse productSearchResponse) {
        mProducts.addAll(mapResponse(productSearchResponse.getData()));
        mProductSearchResponse = productSearchResponse;
        binding.itemProgressBar.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
        Log.i(TAG, "xxx: " + mProducts.size());
    }


    @Override
    public void onProductItemClick(Product product) {
        Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("link", product.getLinks().getDetails());
        intent.putExtra("top_selling", product.getLinks().getRelated());
        startActivity(intent);
    }
}

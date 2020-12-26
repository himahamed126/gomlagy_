package com.onoo.gomlgy.Presentation.ui.fragments.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.onoo.gomlgy.Presentation.presenters.CategoryPresenter;
import com.onoo.gomlgy.Presentation.presenters.SubCategoryPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.SubCategoryView;
import com.onoo.gomlgy.Presentation.ui.activities.impl.ProductListingActivity;
import com.onoo.gomlgy.Presentation.ui.adapters.AllCategoryAdapter;
import com.onoo.gomlgy.Presentation.ui.adapters.SubCategoryAdapter;
import com.onoo.gomlgy.Presentation.ui.fragments.CategoryView;
import com.onoo.gomlgy.Presentation.ui.listeners.AllCategoryClickListener;
import com.onoo.gomlgy.Presentation.ui.listeners.SubCategoryClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.onoo.gomlgy.models.Category;
import com.onoo.gomlgy.models.Product;
import com.onoo.gomlgy.models.SubCategorymodel;

import java.util.ArrayList;
import java.util.List;

public class twofragments extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        AllCategoryClickListener, CategoryView, SubCategoryView, SubCategoryClickListener {
    private View v;
    private CategoryPresenter categoryPresenter;
    private SubCategoryPresenter subCategoryPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Category> mCategories = new ArrayList<>();
    private RecyclerView categoryRv, subCategoriesRv;
    private AllCategoryAdapter allCategoryAdapter;
    private List<SubCategorymodel> subCategories;
    private SubCategoryAdapter subCategoryAdapter;
    private Category category;
//    private SpinKitView spin;
    //    getProductsWithSubcategory apiService;
//    private static final String TAG = "CategoryRecycler";
    //    List<Product> productList = new ArrayList<>();
    //    productsOfSubCategoryAdapter productAdapter;
//    RecyclerView productRv;
//    private ProductListingApiInterface apiService1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_twofragments, null);

        init();
        return v;
    }

    void init() {
        mSwipeRefreshLayout = v.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark, android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        categoryRv = v.findViewById(R.id.category_rv);
        subCategoriesRv = v.findViewById(R.id.sub_categories_rv);

        categoryRv.setLayoutManager(new LinearLayoutManager(getContext()));
        allCategoryAdapter = new AllCategoryAdapter(getActivity(), mCategories,
                twofragments.this);
        categoryRv.setAdapter(allCategoryAdapter);
        categoryPresenter = new CategoryPresenter(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(), this);
        categoryPresenter.getAllCategories();
        subCategoryPresenter = new SubCategoryPresenter(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(), this);
        subCategories = new ArrayList<>();
        subCategoryAdapter = new SubCategoryAdapter(subCategories, this);
        subCategoriesRv.setAdapter(subCategoryAdapter);

//        spin = v.findViewById(R.id.spin_kit);

//        productRv = v.findViewById(R.id.product_rv);
//        productAdapter = new productsOfSubCategoryAdapter(getActivity(), productList,
//        this::onProductClick);
//
//        productRv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
//        productRv.setAdapter(productAdapter);

    }

//    private void getDAta(int subCategoryID) {
//        int categoryId = 1;
//        String url = "https://www.gomlgy.com/api/v1/get-product?category_id=" + categoryId + "&sub_category_id=" + subCategoryID;
//        spin.setVisibility(View.VISIBLE);
//        apiService1 = ApiClient.getClient().create(ProductListingApiInterface.class);
//        Call<ProductListingResponse> call = apiService1.getProducts(url);
//        call.enqueue(new Callback<ProductListingResponse>() {
//            @Override
//            public void onResponse(Call<ProductListingResponse> call, Response<ProductListingResponse> response) {
//                try {
//                    spin.setVisibility(View.GONE);
//                    productList.clear();
//                    productList.addAll(response.body().getData());
//                    productAdapter.notifyDataSetChanged();
//
//                } catch (Exception e) {
//                    Log.e("Exception", e.getMessage());
//                }
//            }
//            @Override
//            public void onFailure(Call<ProductListingResponse> call, Throwable t) {
////                mCallback.onProductDownloadError();
//                Log.e("Exception", t.getMessage());
//            }
//        });
//    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);

    }

    @Override
    public void onCategoryClick(Category category) {
//        Log.i(TAG, "onCategoryClick: " + category.getName());
//        getDAta(category.getId());
        subCategoryPresenter.getSubSubCategories(String.valueOf(category.getId()));
        this.category = category;

    }

    @Override
    public void setAllCategories(List<Category> categories) {
        mCategories.clear();
        mCategories.addAll(categories);
        allCategoryAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);

        subCategoryPresenter.getSubSubCategories(String.valueOf(categories.get(0).getId()));
        category = categories.get(0);

//        if (!categories.isEmpty()) {
//            getDAta(6);
//        }
    }

    @Override
    public void setSubCategories(List<SubCategorymodel> subCategories) {

        this.subCategories.clear();
        this.subCategories.addAll(subCategories);
        subCategoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void onAllProductsClicked() {

        Intent i = new Intent(getActivity(), ProductListingActivity.class);
        i.putExtra(getString(R.string.url), category.getLinks().getProducts());
        i.putExtra(getString(R.string.title), category.getName());
        startActivity(i);

    }

    @Override
    public void onSeeAllProductsOfSubCategoryClicked(int position) {

        Intent i = new Intent(getActivity(), ProductListingActivity.class);
        i.putExtra(getString(R.string.url),
                subCategories.get(position).getLinks().getProducts());
        i.putExtra(getString(R.string.title), subCategories.get(position).getName());
        startActivity(i);

    }

    @Override
    public void onProductClicked(Product product) {
        Toast.makeText(getActivity(), "product", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onProductClick(Product product) {
//        Log.i("jjjjjjjjjjjj", "onProductItemClick: " + product.getLinks().getDetails());
//        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
//        intent.putExtra("product_name", product.getName());
//        intent.putExtra("link", product.getLinks().getDetails());
//        intent.putExtra("top_selling", product.getLinks().getRelated());
//        startActivity(intent);
//    }
}
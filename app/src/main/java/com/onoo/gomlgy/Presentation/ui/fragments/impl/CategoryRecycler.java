package com.onoo.gomlgy.Presentation.ui.fragments.impl;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.ybq.android.spinkit.SpinKitView;
import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.ProductListingResponse;
import com.onoo.gomlgy.Network.services.ProductListingApiInterface;
import com.onoo.gomlgy.Network.services.getProductsWithSubcategory;
import com.onoo.gomlgy.Presentation.presenters.CategoryPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.SubCategoryView;
import com.onoo.gomlgy.Presentation.ui.adapters.AllCategoryAdapter;
import com.onoo.gomlgy.Presentation.ui.adapters.productsOfSubCategoryAdapter;
import com.onoo.gomlgy.Presentation.ui.fragments.CategoryView;
import com.onoo.gomlgy.Presentation.ui.listeners.AllCategoryClickListener;
import com.onoo.gomlgy.Presentation.ui.listeners.AllProductClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.onoo.gomlgy.models.Category;
import com.onoo.gomlgy.models.Product;
import com.onoo.gomlgy.models.SubCategorymodel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class CategoryRecycler extends Fragment implements CategoryView, AllCategoryClickListener,
        SwipeRefreshLayout.OnRefreshListener, SubCategoryView, AllProductClickListener {
    private View v;
    private CategoryPresenter categoryPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Category> mCategories = new ArrayList<>();
    private RecyclerView recyclerView;
    private AllCategoryAdapter adapter;
    getProductsWithSubcategory apiService;
    private static final String TAG = "CategoryRecycler";
    List<Product> productList = new ArrayList<>();
    productsOfSubCategoryAdapter productAdapter;
    Fragment rightFragment;
    RecyclerView productRv;
    private ProductListingApiInterface apiService1;
    SpinKitView spin;
    FragmentManager fragmentManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mCategories.size() > 0) {

            Toast.makeText(getActivity(), "" + mCategories.size(), Toast.LENGTH_SHORT).show();

            fragmentManager = getFragmentManager();
//            rightFragment = fragmentManager.findFragmentById(R.id.subcategory);
            productRv = rightFragment.getView().findViewById(R.id.List_subcategory_products);
            productAdapter = new productsOfSubCategoryAdapter(getActivity(), productList, this);
            productRv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
            productRv.setAdapter(productAdapter);
//
            spin = rightFragment.getView().findViewById(R.id.spin_kit);
//
            spin.setVisibility(View.VISIBLE);
            getDAta(8);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_category_recycler, null);
        apiService = ApiClient.getClient().create(getProductsWithSubcategory.class);


        mSwipeRefreshLayout = v.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_green_dark, android.R.color.holo_orange_dark, android.R.color.holo_blue_dark);
        recyclerView = v.findViewById(R.id.category_list);
        recyclerView.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AllCategoryAdapter(getActivity(), mCategories, CategoryRecycler.this);
        recyclerView.setAdapter(adapter);
        categoryPresenter = new CategoryPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        categoryPresenter.getAllCategories();


        return v;
    }


    @Override
    public void setAllCategories(List<Category> categories) {
        mCategories.clear();
        mCategories.addAll(categories);
        adapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onCategoryClick(Category category) {
        Log.i(TAG, "onCategoryClick: " + category.getName());
        getDAta(category.getId());
    }

    private void getDAta(int subCategoryID) {
        int categoryId = 1;
        String url = "https://www.gomlgy.com/api/v1/get-product?category_id=" + categoryId + "&sub_category_id=" + subCategoryID;
        fragmentManager = getFragmentManager();
        spin = rightFragment.getView().findViewById(R.id.spin_kit);
        apiService1 = ApiClient.getClient().create(ProductListingApiInterface.class);
        Call<ProductListingResponse> call = apiService1.getProducts(url);
        spin.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<ProductListingResponse>() {
            @Override
            public void onResponse(Call<ProductListingResponse> call, Response<ProductListingResponse> response) {
                try {
                    spin.setVisibility(View.GONE);

                    productRv = rightFragment.getView().findViewById(R.id.List_subcategory_products);
//                    productAdapter = new productsOfSubCategoryAdapter(getActivity(), response.body().getData());
                    productRv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                    productRv.setAdapter(productAdapter);


                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductListingResponse> call, Throwable t) {
//                mCallback.onProductDownloadError();
                Log.e("Exception", t.getMessage());
            }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        categoryPresenter.getAllCategories();
    }

    @Override
    public void setSubCategories(List<SubCategorymodel> subCategories) {

    }

//    @Override
//    public void onSubCategoryItemClick(SubCategory subCategory) {
//    }

    @Override
    public void onProductClick(Product product) {

    }
}

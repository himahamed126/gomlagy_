package com.onoo.gomlgy.Presentation.ui.fragments.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.onoo.gomlgy.Presentation.presenters.CategoryPresenter;
import com.onoo.gomlgy.Presentation.presenters.SubCategoryPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.SubCategoryView;
import com.onoo.gomlgy.Presentation.ui.activities.impl.ProductDetailsActivity;
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
import com.onoo.gomlgy.models.Productmodel;
import com.onoo.gomlgy.models.SubCategorymodel;

import java.util.ArrayList;
import java.util.List;

import static com.onoo.gomlgy.Network.ApiClient.BASE_URL;

public class twofragments extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        AllCategoryClickListener, CategoryView, SubCategoryView, SubCategoryClickListener {
    private View v;
    private CategoryPresenter categoryPresenter;
    private SubCategoryPresenter subCategoryPresenter;
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

    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onCategoryClick(Category category) {
        subCategoryPresenter.getSubSubCategories(String.valueOf(category.getId()));
        this.category = category;

    }

    @Override
    public void setAllCategories(List<Category> categories) {
        mCategories.clear();
        mCategories.addAll(categories);
        allCategoryAdapter.notifyDataSetChanged();


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
        i.putExtra(getString(R.string.category_id), String.valueOf(category.getId()));
        startActivity(i);

    }

    @Override
    public void onSeeAllProductsOfSubCategoryClicked(int position) {

        Intent i = new Intent(getActivity(), ProductListingActivity.class);
        i.putExtra(getString(R.string.url), subCategories.get(position).getLinks().getProducts());
        i.putExtra(getString(R.string.sub_category_id),
                subCategories.get(position).getId().toString());
        i.putExtra(getString(R.string.title), subCategories.get(position).getName());
        startActivity(i);

    }

    @Override
    public void onProductClicked(Productmodel product) {
        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("link", BASE_URL + "products/" + product.getId());
        startActivity(intent);
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
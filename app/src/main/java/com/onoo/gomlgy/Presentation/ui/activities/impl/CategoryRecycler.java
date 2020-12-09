package com.onoo.gomlgy.Presentation.ui.activities.impl;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onoo.gomlgy.Models.Category;
import com.onoo.gomlgy.Models.SubCategory;
import com.onoo.gomlgy.Models.collectionmodel;
import com.onoo.gomlgy.Network.services.getProductsWithSubcategory;
import com.onoo.gomlgy.Presentation.presenters.CategoryPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.SubCategoryView;
import com.onoo.gomlgy.Presentation.ui.adapters.AllCategoryAdapter;
import com.onoo.gomlgy.Presentation.ui.adapters.AllProductsAndSubcategoryAdapter;
import com.onoo.gomlgy.Presentation.ui.fragments.CategoryView;
import com.onoo.gomlgy.Presentation.ui.listeners.AllCategoryClickListener;
import com.onoo.gomlgy.Presentation.ui.listeners.SubCategoryClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class CategoryRecycler extends Fragment implements CategoryView, AllCategoryClickListener, SwipeRefreshLayout.OnRefreshListener, SubCategoryView, SubCategoryClickListener {
    private View v;
    private CategoryPresenter categoryPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Category> mCategories = new ArrayList<>();
    private RecyclerView recyclerView;
    private AllCategoryAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mCategories.size() > 0) {
            FragmentManager fragmentManager = getFragmentManager();
            Fragment rightFragment = fragmentManager.findFragmentById(R.id.subcategory);
            SpinKitView spin = rightFragment.getView().findViewById(R.id.spin_kit);
            spin.setVisibility(View.VISIBLE);
            getDAta(mCategories.get(0).getName(),mCategories.get(0).getId(), rightFragment, spin);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_category_recycler, null);
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
        FragmentManager fragmentManager = getFragmentManager();
        Fragment rightFragment = fragmentManager.findFragmentById(R.id.subcategory);
        SpinKitView spin = rightFragment.getView().findViewById(R.id.spin_kit);
        spin.setVisibility(View.VISIBLE);
        getDAta(category.getName(),category.getId(), rightFragment, spin);
    }
    private void getDAta(String Categoriy,int cat_id, Fragment rightFragment, SpinKitView spin) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .protocols(Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1, Protocol.SPDY_3))
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20000, TimeUnit.SECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.gomlgy.com/api/v1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        getProductsWithSubcategory conn = retrofit.create(getProductsWithSubcategory.class);
        Call<collectionmodel> getProducts = conn.get_Products_With_SubCategory(Categoriy);
        getProducts.enqueue(new Callback<collectionmodel>() {
            @Override
            public void onResponse(Call<collectionmodel> call, Response<collectionmodel> response) {
                collectionmodel model = response.body();
                Log.i("qwe", "onResponse: "+call.request().url());
                RecyclerView recyclerView = rightFragment.getView().findViewById(R.id.List_subcategory_products);
                AllProductsAndSubcategoryAdapter adapter = new AllProductsAndSubcategoryAdapter(getActivity(), model,cat_id);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
                spin.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<collectionmodel> call, Throwable t) {
                Log.i("asd", "onFailure: " + t.getMessage());
            }
        });
    }
    @Override
    public void onRefresh()
    {
        mSwipeRefreshLayout.setRefreshing(true);
        categoryPresenter.getAllCategories();
    }
    @Override
    public void setSubCategories(List<SubCategory> subCategories)
    {

    }
    @Override
    public void onSubCategoryItemClick(SubCategory subCategory)
    {

    }
}

package com.onoo.gomlgy.Presentation.ui.fragments.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onoo.gomlgy.Models.Category;
import com.onoo.gomlgy.Models.SubCategorymodel;
import com.onoo.gomlgy.Presentation.presenters.CategoryPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.impl.SubCategoryActivity;
import com.onoo.gomlgy.Presentation.ui.adapters.AllCategoryAdapter;
import com.onoo.gomlgy.Presentation.ui.fragments.CategoryView;
import com.onoo.gomlgy.Presentation.ui.listeners.AllCategoryClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.AppConfig;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CategoriesFragment extends Fragment implements CategoryView, AllCategoryClickListener, SwipeRefreshLayout.OnRefreshListener {
    private View v;
    private CategoryPresenter categoryPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<SubCategorymodel> mCategories = new ArrayList<>();
    private RecyclerView recyclerView;
    private AllCategoryAdapter adapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_categories, null);

        mSwipeRefreshLayout = v.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        recyclerView = v.findViewById(R.id.category_list);
        recyclerView.hasFixedSize();
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AllCategoryAdapter(getActivity(), mCategories, CategoriesFragment.this);
        recyclerView.addItemDecoration( new LayoutMarginDecoration( 1,  AppConfig.convertDpToPx(getContext(), 10)) );
        recyclerView.setAdapter(adapter);

        categoryPresenter = new CategoryPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        categoryPresenter.getAllCategories();

        return v;
    }

    @Override
    public void setAllCategories(List<SubCategorymodel> categories) {
        mCategories.clear();
        mCategories.addAll(categories);
        adapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onCategoryClick(SubCategorymodel category) {
        Intent intent = new Intent(getContext(), SubCategoryActivity.class);
//        intent.putExtra("category", category);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        categoryPresenter.getAllCategories();
    }
}

package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.onoo.gomlgy.Models.Category;
import com.onoo.gomlgy.Presentation.presenters.CategoryPresenter;
import com.onoo.gomlgy.Presentation.ui.adapters.AllCategoryAdapter;
import com.onoo.gomlgy.Presentation.ui.fragments.CategoryView;
import com.onoo.gomlgy.Presentation.ui.fragments.impl.CategoriesFragment;
import com.onoo.gomlgy.Presentation.ui.listeners.AllCategoryClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.AppConfig;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.ArrayList;
import java.util.List;


public class twofragments  extends Fragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        insertNestedFragment();
    }

    // Embeds the child fragment dynamically
    private void insertNestedFragment() {
        Fragment childFragment = new CategoryRecycler();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.category, childFragment).commit();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_twofragments, container, false);

    }
}
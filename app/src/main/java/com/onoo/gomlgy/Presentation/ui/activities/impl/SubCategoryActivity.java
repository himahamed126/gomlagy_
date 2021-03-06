package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.Presentation.presenters.SubCategoryPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.SubCategoryView;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.AppConfig;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.onoo.gomlgy.models.Category;
import com.onoo.gomlgy.models.SubCategorymodel;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.List;

public class SubCategoryActivity extends BaseActivity implements SubCategoryView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_sub_category);

        Category category = (Category) getIntent().getSerializableExtra("category");

        initializeActionBar();
        setTitle(category.getName());

        SubCategoryPresenter subCategoryPresenter = new SubCategoryPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        subCategoryPresenter.getSubSubCategories(String.valueOf(category.getId()));
    }

    @Override
    public void setSubCategories(List<SubCategorymodel> subCategories) {
        RecyclerView recyclerView = findViewById(R.id.subcategory_list);
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LayoutMarginDecoration(1, AppConfig.convertDpToPx(getApplicationContext(), 10)));
//        SubCategoryAdapter adapter = new SubCategoryAdapter(this, subCategories, this);
//        recyclerView.setAdapter(adapter);
    }

//    @Override
//    public void onSubCategoryItemClick(SubCategory subCategory) {
//        Intent intent = new Intent(this, ProductListingActivity.class);
//        intent.putExtra("title", subCategory.getName());
//        intent.putExtra("url", subCategory.getLinks().getProducts());
//        startActivity(intent);
//    }
}

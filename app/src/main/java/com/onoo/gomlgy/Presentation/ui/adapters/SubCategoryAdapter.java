package com.onoo.gomlgy.Presentation.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.onoo.gomlgy.Presentation.ui.listeners.SubCategoryClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Utils.GlideImageLoader;
import com.onoo.gomlgy.databinding.SubCategoryHeaderItemBinding;
import com.onoo.gomlgy.databinding.SubCategoryListItemBinding;
import com.onoo.gomlgy.models.SubCategorymodel;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SubCategorymodel> subCategories;
    private LayoutInflater layoutInflater;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    SubCategoryClickListener subCategoryClickListener;

    // data is passed into the constructor
    public SubCategoryAdapter(List<SubCategorymodel> subCategories,
                              SubCategoryClickListener clickListener) {
        this.subCategories = subCategories;
        subCategoryClickListener = clickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_HEADER) {
            SubCategoryHeaderItemBinding subCategoryHeaderBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.sub_category_header_item,
                            parent, false);
            return new ViewHeaderHolder(subCategoryHeaderBinding);
        } else if (viewType == TYPE_ITEM) {
            SubCategoryListItemBinding subCategoryBinding = DataBindingUtil.inflate(layoutInflater,
                    R.layout.sub_category_list_item, parent, false);
            return new ViewHolder(subCategoryBinding);
        } else
            return null;

    }

    // binds the data to the view in each row
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {

            ((ViewHolder) holder).subCategoryBinding.setSubCategory(subCategories.get(position));
            if (subCategories.get(position).getProducts() != null &&
                    subCategories.get(position).getProducts().size() > 0) {
                ((ViewHolder) holder).subCategoryBinding.productsRv.setAdapter(new SubSubCategoryAdapter(subCategories.get(position)
                                .getProducts(), productPosition ->
                                subCategoryClickListener.onProductClicked(subCategories
                                        .get(position).getProducts().get(productPosition))));
            }

            ((ViewHolder) holder).subCategoryBinding.seeAllTv.setOnClickListener(view ->
                    subCategoryClickListener.onSeeAllProductsOfSubCategoryClicked(position));

        } else if (holder instanceof ViewHeaderHolder) {
            ((ViewHeaderHolder) holder).subCategoryHeaderBinding.allProductsCl
                    .setOnClickListener(view -> subCategoryClickListener.onAllProductsClicked());
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return subCategories.size();
    }

    //    Determine whether the item is the header item or not
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else
            return TYPE_ITEM;
    }

    // stores and recycles views as they are scrolled off screen
    //    Returns sub category header item (first item) binding
    class ViewHeaderHolder extends RecyclerView.ViewHolder {
        SubCategoryHeaderItemBinding subCategoryHeaderBinding;

        ViewHeaderHolder(SubCategoryHeaderItemBinding subCategoryHeaderBinding) {
            super(subCategoryHeaderBinding.getRoot());
            this.subCategoryHeaderBinding = subCategoryHeaderBinding;
        }
    }

    // stores and recycles views as they are scrolled off screen
    //    Returns sub category item (not the first item) binding
    class ViewHolder extends RecyclerView.ViewHolder {
        SubCategoryListItemBinding subCategoryBinding;

        ViewHolder(SubCategoryListItemBinding subCategoryBinding) {
            super(subCategoryBinding.getRoot());
            this.subCategoryBinding = subCategoryBinding;
        }

    }


}

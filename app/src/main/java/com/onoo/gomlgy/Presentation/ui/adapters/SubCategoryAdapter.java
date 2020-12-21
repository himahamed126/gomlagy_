package com.onoo.gomlgy.Presentation.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.R;
import com.onoo.gomlgy.databinding.SubCategoryListItemBinding;
import com.onoo.gomlgy.models.SubCategory;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    private List<SubCategory> subCategories;
    private LayoutInflater layoutInflater;

    // data is passed into the constructor
    public SubCategoryAdapter(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        SubCategoryListItemBinding subCategoryBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.sub_category_list_item, parent, false);
        return new ViewHolder(subCategoryBinding);
    }

    // binds the data to the view in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.subCategoryBinding.setSubCategory(subCategories.get(position));
        if (subCategories.get(position).getSubSubCategories().getData().size() > 0) {
            holder.subCategoryBinding.productsRv.setAdapter(new SubSubCategoryAdapter(subCategories
                    .get(position).getSubSubCategories().getData()));
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return subCategories.size();
    }

    // stores and recycles views as they are scrolled off screen
    class ViewHolder extends RecyclerView.ViewHolder {
        SubCategoryListItemBinding subCategoryBinding;

        ViewHolder(SubCategoryListItemBinding subCategoryBinding) {
            super(subCategoryBinding.getRoot());
            this.subCategoryBinding = subCategoryBinding;
        }

    }
}

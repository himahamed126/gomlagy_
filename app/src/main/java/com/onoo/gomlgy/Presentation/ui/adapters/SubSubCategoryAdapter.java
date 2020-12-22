package com.onoo.gomlgy.Presentation.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.Presentation.ui.listeners.SubCategoryClickListener;
import com.onoo.gomlgy.Presentation.ui.listeners.SubSubCategoryClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.databinding.ProductListItemBinding;
import com.onoo.gomlgy.models.SubSubCategory;

import java.util.List;

public class SubSubCategoryAdapter extends RecyclerView.Adapter<SubSubCategoryAdapter.ViewHolder> {

    private List<SubSubCategory> subSubCategories;
    private SubSubCategoryClickListener subSubCategoryClickListener;
    private LayoutInflater layoutInflater;

    // data is passed into the constructor
    public SubSubCategoryAdapter(List<SubSubCategory> subSubCategories,
                                 SubSubCategoryClickListener clickListener) {
        this.subSubCategories = subSubCategories;
        subSubCategoryClickListener = clickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ProductListItemBinding productItemBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.product_list_item, parent, false);
        return new SubSubCategoryAdapter.ViewHolder(productItemBinding);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productListItemBinding.setProduct(subSubCategories.get(position));
        holder.productListItemBinding.productItemCl.setOnClickListener(view ->
                subSubCategoryClickListener.onSubSubCategoryClick(position));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return subSubCategories.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ProductListItemBinding productListItemBinding;

        ViewHolder(ProductListItemBinding productListItemBinding) {
            super(productListItemBinding.getRoot());
            this.productListItemBinding = productListItemBinding;
        }
    }
}

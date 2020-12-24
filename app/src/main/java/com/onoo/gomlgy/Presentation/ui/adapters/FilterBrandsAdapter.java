package com.onoo.gomlgy.Presentation.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.Presentation.ui.listeners.FilterBrandCallback;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.databinding.ItemBrandBinding;
import com.onoo.gomlgy.models.BrandData;

import java.util.List;

public class FilterBrandsAdapter extends RecyclerView.Adapter<FilterBrandsAdapter.ViewHolder> {

    private List<BrandData> brandDataList;
    private LayoutInflater layoutInflater;
    private FilterBrandCallback filterBrandCallback;

    // data is passed into the constructor
    public FilterBrandsAdapter(List<BrandData> brandDataList,
                               FilterBrandCallback filterBrandCallback) {
        this.brandDataList = brandDataList;
        this.filterBrandCallback = filterBrandCallback;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public FilterBrandsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {

        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ItemBrandBinding itemBrandBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_brand, parent, false);
        return new ViewHolder(itemBrandBinding);

    }

    // binds the data to the view in each row
    @Override
    public void onBindViewHolder(@NonNull FilterBrandsAdapter.ViewHolder holder, int position) {

        holder.brandBinding.setBrand(brandDataList.get(position));
        if (brandDataList.get(position).getSelected() != null && brandDataList.get(position)
                .getSelected())
            holder.brandBinding.brandIsCheckedIv.setVisibility(View.VISIBLE);
        else
            holder.brandBinding.brandIsCheckedIv.setVisibility(View.GONE);

        holder.brandBinding.brandCl.setOnClickListener(view ->
                filterBrandCallback.onBrandSelected(position));

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return brandDataList.size();
    }

    // stores and recycles views as they are scrolled off screen
    //    Returns sub category item (not the first item) binding
    class ViewHolder extends RecyclerView.ViewHolder {
        ItemBrandBinding brandBinding;

        ViewHolder(ItemBrandBinding brandBinding) {
            super(brandBinding.getRoot());
            this.brandBinding = brandBinding;
        }

    }
}

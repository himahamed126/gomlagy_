package com.onoo.gomlgy.Presentation.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.Presentation.ui.listeners.ProductClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.databinding.ItemProductBinding;
import com.onoo.gomlgy.models.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private List<Product> mProducts;
    private LayoutInflater layoutInflater;
    private ProductClickListener productClickListener;

    // data is passed into the constructor
    public ProductsAdapter(List<Product> mProducts, ProductClickListener productClickListener) {
        this.mProducts = mProducts;
        this.productClickListener = productClickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ItemProductBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_product, parent, false);
        return new ProductsAdapter.ViewHolder(binding);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {

        holder.binding.setProduct(mProducts.get(position));
        holder.binding.productCv.setOnClickListener(view ->
                productClickListener.onProductItemClick(mProducts.get(position)));

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mProducts.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding binding;

        ViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

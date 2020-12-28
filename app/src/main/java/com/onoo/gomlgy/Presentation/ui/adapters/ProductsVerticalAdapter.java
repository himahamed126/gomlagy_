package com.onoo.gomlgy.Presentation.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.Presentation.ui.listeners.ProductClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Utils.HelperMethod;
import com.onoo.gomlgy.Utils.ItemAnimation;
import com.onoo.gomlgy.databinding.ItemProductBinding;
import com.onoo.gomlgy.databinding.ItemProductVerticalBinding;
import com.onoo.gomlgy.models.Product;

import java.util.List;

public class ProductsVerticalAdapter extends RecyclerView.Adapter<ProductsVerticalAdapter.ViewHolder> {
    private List<Product> mProducts;
    private LayoutInflater layoutInflater;
    private ProductClickListener productClickListener;

    // data is passed into the constructor
    public ProductsVerticalAdapter(List<Product> mProducts, ProductClickListener productClickListener) {
        this.mProducts = mProducts;
        this.productClickListener = productClickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ItemProductVerticalBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_product_vertical, parent, false);
        return new ProductsVerticalAdapter.ViewHolder(binding);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ProductsVerticalAdapter.ViewHolder holder, int position) {

        holder.binding.setProduct(mProducts.get(position));
        HelperMethod.setAnimation(holder.itemView, position, ItemAnimation.LEFT_RIGHT,
                -1, true);
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
        ItemProductVerticalBinding binding;

        ViewHolder(ItemProductVerticalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

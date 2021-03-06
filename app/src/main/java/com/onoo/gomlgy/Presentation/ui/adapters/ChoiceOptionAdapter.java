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
import com.onoo.gomlgy.Presentation.ui.listeners.SubSubCategoryClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Utils.GlideImageLoader;
import com.onoo.gomlgy.databinding.ProductListItemBinding;
import com.onoo.gomlgy.databinding.SubCategoryHeaderItemBinding;
import com.onoo.gomlgy.databinding.SubCategoryListItemBinding;
import com.onoo.gomlgy.models.Productmodel;
import com.onoo.gomlgy.models.SubCategorymodel;

import java.util.List;

public class ChoiceOptionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Productmodel> products;
    private SubSubCategoryClickListener subSubCategoryClickListener;
    private LayoutInflater layoutInflater;

    // data is passed into the constructor
    public ChoiceOptionAdapter(List<Productmodel> products,
                                 SubSubCategoryClickListener clickListener) {
        this.products = products;
        subSubCategoryClickListener = clickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ChoiceOptionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ProductListItemBinding productItemBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.product_list_item, parent, false);
        return new ChoiceOptionAdapter.ViewHolder(productItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Productmodel productmodel=products.get(position);
//        holder.productListItemBinding.setProduct(products.get(position));
//        setImage(holder.productListItemBinding.productImageIv, holder.productListItemBinding.productPb, productmodel.getThumbnailPath().get(0));
//
//        holder.productListItemBinding.productItemCl.setOnClickListener(view ->
//                subSubCategoryClickListener.onSubSubCategoryClick(position));
    }


    // total number of rows
    @Override
    public int getItemCount() {
        return products.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ProductListItemBinding productListItemBinding;

        ViewHolder(ProductListItemBinding productListItemBinding) {
            super(productListItemBinding.getRoot());
            this.productListItemBinding = productListItemBinding;
        }
    }
    void setImage(ImageView imageView, ProgressBar progressBar, String imageUrl) {
        RequestOptions requestOptions = new RequestOptions()
                .fitCenter()
                .error(R.drawable.logo_gomlgy);

        new GlideImageLoader(imageView, progressBar).load(imageUrl, requestOptions);
    }
}

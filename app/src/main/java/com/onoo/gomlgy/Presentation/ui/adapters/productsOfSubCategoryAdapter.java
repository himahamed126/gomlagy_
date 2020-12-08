package com.onoo.gomlgy.Presentation.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.onoo.gomlgy.Models.Category;
import com.onoo.gomlgy.Models.Product;
import com.onoo.gomlgy.Models.Productmodel;
import com.onoo.gomlgy.Presentation.ui.listeners.AllCategoryClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Utils.AppConfig;

import java.util.List;

public class productsOfSubCategoryAdapter extends RecyclerView.Adapter<productsOfSubCategoryAdapter.ViewHolder> {

    private Context context;
    private List<Productmodel> Products;
    private LayoutInflater mInflater;
    private AllCategoryClickListener mClickListener;
    int selectedPosition=-1;
    // data is passed into the constructor
    public productsOfSubCategoryAdapter(Context context, List<Productmodel> products) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.Products = products;

    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.product_box_subcategory, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(Products.get(position));
    }
    // total number of rows
    @Override
    public int getItemCount() {
        return Products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView discounted_price, price;
        TextView name;
        TextView sales;
        RatingBar ratingBar;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            discounted_price = itemView.findViewById(R.id.product_discounted_price);
            price = itemView.findViewById(R.id.product_price);
            name = itemView.findViewById(R.id.product_name);
            ratingBar = itemView.findViewById(R.id.product_rating);
            sales = itemView.findViewById(R.id.product_rating_count);
        }

        public void bind(Productmodel product) {
           Glide.with(context).load(AppConfig.ASSET_URL + product.getThumbnailImg()).into(image);

//
            name.setText(product.getName());
        }
    }
}

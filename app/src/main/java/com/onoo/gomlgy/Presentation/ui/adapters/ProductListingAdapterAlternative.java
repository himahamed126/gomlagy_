package com.onoo.gomlgy.Presentation.ui.adapters;

import android.content.Context;
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
import com.onoo.gomlgy.Models.Product;
import com.onoo.gomlgy.Models.Productmodel;
import com.onoo.gomlgy.Presentation.ui.listeners.ProductClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Utils.AppConfig;

import java.util.List;

public class ProductListingAdapterAlternative extends RecyclerView.Adapter<ProductListingAdapterAlternative.ViewHolder> {

    private List<Productmodel> mProducts;
    private LayoutInflater mInflater;
    private Context context;
    private ProductClickListener productClickListener;

    // data is passed into the constructor
    public ProductListingAdapterAlternative(Context context, List<Productmodel> mProducts) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mProducts = mProducts;
    //    this.productClickListener = productClickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.product_box_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mProducts.get(position));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    // convenience method for getting data at click position
    public Productmodel getItem(int id) {
        return mProducts.get(id);
    }

    // stores and recycles views as they are scrolled off screen
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
            discounted_price.setText(AppConfig.convertPrice(context, Double.parseDouble(product.getDiscount().toString())));//todays_deal
            price.setText(AppConfig.convertPrice(context, Double.parseDouble(product.getPurchasePrice().toString())));//base_discounted_price
            if (product.getDiscount().equals(product.getPurchasePrice())) {
                price.setVisibility(View.GONE);
            }
            price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            name.setText(product.getName());
            ratingBar.setRating(product.getRating());
            sales.setText(product.getNumOfSale()+" sold");

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    productClickListener.onProductItemClick(product);
//                }
//            });
        }
    }
}


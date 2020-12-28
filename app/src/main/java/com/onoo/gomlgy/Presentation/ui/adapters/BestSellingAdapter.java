package com.onoo.gomlgy.Presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.models.Product;
import com.onoo.gomlgy.Presentation.ui.listeners.ProductClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Utils.AppConfig;
import com.bumptech.glide.Glide;
import com.onoo.gomlgy.Utils.HelperMethod;
import com.onoo.gomlgy.Utils.ItemAnimation;

import java.util.List;

public class BestSellingAdapter extends RecyclerView.Adapter<BestSellingAdapter.ViewHolder> {
    private List<Product> mProducts;
    private LayoutInflater mInflater;
    private Context context;
    private ProductClickListener productClickListener;
    private int lastPosition = -1;
    private boolean on_attach = true;

    // data is passed into the constructor
    public BestSellingAdapter(Context context, List<Product> mProducts, ProductClickListener productClickListener) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mProducts = mProducts;
        this.productClickListener = productClickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_product_vertical, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull BestSellingAdapter.ViewHolder holder, int position) {
        holder.bind(mProducts.get(position));
        HelperMethod.setAnimation(holder.itemView, position, ItemAnimation.LEFT_RIGHT, lastPosition, on_attach);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    // convenience method for getting data at click position
    public Product getItem(int id) {
        return mProducts.get(id);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView discounted_price, price;
        TextView name;
        RatingBar ratingBar;
        TextView price1, price2, price3;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            price1 = itemView.findViewById(R.id.price_1);
            price2 = itemView.findViewById(R.id.price_2);
            price3 = itemView.findViewById(R.id.price_3);
            name = itemView.findViewById(R.id.product_name);
            ratingBar = itemView.findViewById(R.id.product_rating);
        }

        public void bind(Product product) {
            Glide.with(context).load(AppConfig.ASSET_URL + product.getThumbnailImage()).into(image);
            price1.setText(AppConfig.convertPrice2(context, product.getBaseDiscountedPrice()));
            price2.setText(AppConfig.convertPrice2(context, product.getBaseDiscountedPrice()));
            price3.setText(AppConfig.convertPrice2(context, product.getBaseDiscountedPrice()));
            name.setText(product.getName());
            ratingBar.setRating(product.getRating());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    productClickListener.onProductItemClick(product);
                }
            });
        }
    }
}

package com.onoo.gomlgy.Presentation.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.onoo.gomlgy.models.Product;
import com.onoo.gomlgy.Presentation.ui.activities.impl.ProductDetailsActivity;
import com.onoo.gomlgy.Presentation.ui.listeners.ProductClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Utils.AppConfig;
import com.onoo.gomlgy.Utils.HelperMethod;
import com.onoo.gomlgy.Utils.ItemAnimation;

import java.util.List;

public class HomeSubCategoryAdapter extends RecyclerView.Adapter<HomeSubCategoryAdapter.ViewHolder> {
    private List<Product> mProducts;
    private LayoutInflater mInflater;
    private Context context;
    private ProductClickListener productClickListener;
    private int lastPosition = -1;
    private boolean on_attach = true;

    // data is passed into the constructor
    public HomeSubCategoryAdapter(Context context, List<Product> mProducts) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mProducts = mProducts;
        this.productClickListener = productClickListener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.home_sub_category_product_box, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull HomeSubCategoryAdapter.ViewHolder holder, int position) {
        holder.bind(mProducts.get(position));
        HelperMethod.setAnimation(holder.itemView, position, ItemAnimation.BOTTOM_UP, lastPosition, on_attach);
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
        TextView discounted_price;
        TextView price;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            discounted_price = itemView.findViewById(R.id.product_discounted_price);
            //price = itemView.findViewById(R.id.product_price);
        }

        public void bind(Product product) {
            Glide.with(context).load(AppConfig.ASSET_URL + product.getThumbnailImage()).into(image);
            discounted_price.setText(AppConfig.convertPrice(context, product.getBaseDiscountedPrice()));
//            price.setText(AppConfig.convertPrice(product.getBaseDiscountedPrice()));
//            if (product.getBaseDiscountedPrice().equals(product.getBasePrice())) {
//                price.setVisibility(View.GONE);
//            }
//            price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    productClickListener.onProductItemClick(product);
                    Log.i("jjjjjjjjjjjj", "onProductItemClick: " + product.getLinks().getDetails());
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.putExtra("product_name", product.getName());
                    intent.putExtra("link", product.getLinks().getDetails());
                    intent.putExtra("top_selling", product.getLinks().getRelated());
                    context.startActivity(intent);
                }
            });
        }
    }
}

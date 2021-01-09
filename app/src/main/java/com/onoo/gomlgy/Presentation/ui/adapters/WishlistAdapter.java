package com.onoo.gomlgy.Presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.onoo.gomlgy.Presentation.ui.listeners.WishlistProductClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Utils.AppConfig;
import com.onoo.gomlgy.databinding.WishlistProductBoxBinding;
import com.onoo.gomlgy.models.WishlistModel;

import java.util.List;

import static com.onoo.gomlgy.Utils.AppConfig.calcLessPrice;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<WishlistModel> mProducts;
    private LayoutInflater mInflater;
    private Context context;
    private WishlistProductClickListener productClickListener;

    // data is passed into the constructor
    public WishlistAdapter(Context context, List<WishlistModel> mProducts, WishlistProductClickListener productClickListener) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mProducts = mProducts;
        this.productClickListener = productClickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        WishlistProductBoxBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.wishlist_product_box, parent, false);
        return new WishlistAdapter.ViewHolder(binding);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WishlistModel product = mProducts.get(position);
        double p1 = product.getProduct().getUnitPrice(), p2 = product.getProduct().getUnitPrice2(), p3 = product.getProduct().getUnitPrice3();
        WishlistProductBoxBinding verBinding = holder.verBinding;
        calcLessPrice(p1, p2, p3, verBinding.price1, verBinding.price2, verBinding.price3, context);
        verBinding.setProduct(product);
        verBinding.productCv.setOnClickListener(view -> productClickListener.onProductItemClick(product.getProduct()));
        Glide.with(context).load(AppConfig.ASSET_URL + product.getProduct().getThumbnailImage()).into(verBinding.productImage);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    // convenience method for getting data at click position
    public WishlistModel getItem(int id) {
        return mProducts.get(id);
    }

    // stores and recycles views as they are scrolled off screen
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView image;
//        TextView discounted_price, price;
//        TextView name;
//        TextView sales;
//        RatingBar ratingBar;
//
//        ViewHolder(View itemView) {
//            super(itemView);
//            image = itemView.findViewById(R.id.product_image);
//            discounted_price = itemView.findViewById(R.id.product_discounted_price);
//            price = itemView.findViewById(R.id.product_price);
//            name = itemView.findViewById(R.id.product_name);
//            ratingBar = itemView.findViewById(R.id.product_rating);
//            sales = itemView.findViewById(R.id.product_rating_count);
//        }
//
//        public void bind(WishlistModel wishlistModel) {
//            Glide.with(context).load(AppConfig.ASSET_URL + wishlistModel.getProduct().getThumbnailImage()).into(image);
////            discounted_price.setText(AppConfig.convertPrice(context, wishlistModel.getProduct().getBaseDiscountedPrice()));
////            price.setText(AppConfig.convertPrice(context, wishlistModel.getProduct().getBasePrice()));
//            if (wishlistModel.getProduct().getBaseDiscountedPrice().equals(wishlistModel.getProduct().getBasePrice())) {
//                price.setVisibility(View.GONE);
//            }
//            price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//            name.setText(wishlistModel.getProduct().getName());
//            ratingBar.setRating(wishlistModel.getProduct().getRating());
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    productClickListener.onProductItemClick(wishlistModel.getProduct());
//                }
//            });
//        }
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private WishlistProductBoxBinding verBinding;

        ViewHolder(WishlistProductBoxBinding binding) {
            super(binding.getRoot());
            this.verBinding = binding;

        }
    }
}


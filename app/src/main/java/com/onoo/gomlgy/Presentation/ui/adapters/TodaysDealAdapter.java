package com.onoo.gomlgy.Presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class TodaysDealAdapter extends RecyclerView.Adapter<TodaysDealAdapter.ViewHolder> {
    private List<Product> mProducts;
    private LayoutInflater mInflater;
    private Context context;
    private ProductClickListener productClickListener;
    private int lastPosition = -1;
    private boolean on_attach = true;

    // data is passed into the constructor
    public TodaysDealAdapter(Context context, List<Product> mProducts, ProductClickListener productClickListener) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mProducts = mProducts;
        this.productClickListener = productClickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.todays_deal_product_box, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull TodaysDealAdapter.ViewHolder holder, int position) {
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
        TextView price1, price2, price3;
        TextView price;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            price1 = itemView.findViewById(R.id.price_1);
            price2 = itemView.findViewById(R.id.price_2);
            price3 = itemView.findViewById(R.id.price_3);
            //price = itemView.findViewById(R.id.product_price);
        }

        public void bind(Product product) {
            Glide.with(context).load(AppConfig.ASSET_URL +
                    product.getThumbnailImage()).into(image);

//            double p1 = product.getUnitPrice();
//            double p2 = product.getUnitPrice2();
//            double p3 = product.getUnitPrice3();

//            AppConfig.calcLessPrice(p1, p2, p3, price1, price2, price3, context);

            itemView.setOnClickListener(view -> productClickListener.onProductItemClick(product));
        }
    }


}

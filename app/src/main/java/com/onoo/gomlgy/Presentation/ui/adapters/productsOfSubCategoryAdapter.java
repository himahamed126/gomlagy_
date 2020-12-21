package com.onoo.gomlgy.Presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.onoo.gomlgy.models.Product;
import com.onoo.gomlgy.Presentation.ui.listeners.AllProductClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Utils.AppConfig;

import java.util.ArrayList;
import java.util.List;

public class productsOfSubCategoryAdapter extends RecyclerView.Adapter<productsOfSubCategoryAdapter.ViewHolder> {

    private Context context;
    private List<Product> productList = new ArrayList<>();
    private LayoutInflater mInflater;
    private AllProductClickListener mClickListener;
    int selectedPosition = -1;

    // data is passed into the constructor
    public productsOfSubCategoryAdapter(Context context, List<Product> products, AllProductClickListener mClickListener) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.productList = products;
        this.mClickListener = mClickListener;

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
        Product productmodel = productList.get(position);
        holder.bind(productmodel);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.product_name);
            itemView.setOnClickListener(this);
        }

        public void bind(Product product) {
            Glide.with(context).load(AppConfig.ASSET_URL + product.getThumbnailImage()).into(image);
            name.setText(product.getName());
        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
            notifyItemChanged(selectedPosition);
            selectedPosition = getAdapterPosition();
            if (mClickListener != null)
                mClickListener.onProductClick(productList.get(getAdapterPosition()));

        }
    }
}

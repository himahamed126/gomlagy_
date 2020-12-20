package com.onoo.gomlgy.Presentation.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.Models.collectionmodel;
import com.onoo.gomlgy.Presentation.ui.activities.impl.ProductListingActivity;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Utils.HelperMethod;
import com.onoo.gomlgy.Utils.ItemAnimation;

import static com.facebook.FacebookSdk.getApplicationContext;


public class AllProductsAndSubcategoryAdapter extends RecyclerView.Adapter<AllProductsAndSubcategoryAdapter.ViewHolder> {

    private Context context;
    private collectionmodel myProductsAndSubcatagory;
    private LayoutInflater mInflater;
    int categoryId;

    private int lastPosition = -1;
    private boolean on_attach = true;


    public AllProductsAndSubcategoryAdapter(Context context, collectionmodel products, int categoryId) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.myProductsAndSubcatagory = products;
        this.categoryId = categoryId;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.category_subcategory_products_cells, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        collectionmodel model = myProductsAndSubcatagory;
//        holder.subcategoryTitle.setText(model.getData().get(0).getSubCategories().get(position).getName());
//        productsOfSubCategoryAdapter adapter = new productsOfSubCategoryAdapter(getApplicationContext(), model.getData().get(0).getSubCategories().get(position).getProducts());
        holder.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
//        holder.recyclerView.setAdapter(adapter);

        holder.itemView.setOnClickListener(v -> {
            int subCategoryID = model.getData().get(0).getSubCategories().get(position).getId();
            Log.i("URLLLL", "https://www.gomlgy.com/api/v1/get-product?category_id=" + categoryId + "&sub_category_id=" + subCategoryID);
            Intent i = new Intent(getApplicationContext(), ProductListingActivity.class);
            i.putExtra("url", "https://www.gomlgy.com/api/v1/get-product?category_id=" + categoryId + "&sub_category_id=" + subCategoryID);
            i.putExtra("title", model.getData().get(0).getSubCategories().get(position).getName());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        });
        HelperMethod.setAnimation(holder.itemView, position, ItemAnimation.BOTTOM_UP, lastPosition, on_attach);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return myProductsAndSubcatagory.getData().get(0).getSubCategories().size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subcategoryTitle;
        TextView seeAll;
        RecyclerView recyclerView;

        ViewHolder(View itemView) {
            super(itemView);
            subcategoryTitle = itemView.findViewById(R.id.subcategoryText);
            seeAll = itemView.findViewById(R.id.SEEALL);
            recyclerView = itemView.findViewById(R.id.product_list_card);
        }
    }
}

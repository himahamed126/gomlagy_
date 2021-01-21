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
import com.onoo.gomlgy.Utils.AppConfig;
import com.onoo.gomlgy.models.CatDrawer;
import com.onoo.gomlgy.Presentation.ui.listeners.DrawerClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Utils.HelperMethod;
import com.onoo.gomlgy.Utils.ItemAnimation;

import java.util.List;

public class DrawerCategoryAdapter extends RecyclerView.Adapter<DrawerCategoryAdapter.ViewHolder> {

    private Context context;
    private List<CatDrawer> mCategories;
    private LayoutInflater mInflater;
    private DrawerClickListener mClickListener;
    private int lastPosition = -1;
    private boolean on_attach = true;

    // data is passed into the constructor
    public DrawerCategoryAdapter(Context context, List<CatDrawer> categories, DrawerClickListener listener) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mCategories = categories;
        this.mClickListener = listener;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.drawer_category_list_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mCategories.get(position));
        HelperMethod.setAnimation(holder.itemView, position, ItemAnimation.RIGHT_LEFT, lastPosition, on_attach);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.drawer_item_iv);
            textView = itemView.findViewById(R.id.drawer_item_tv);
        }

        public void bind(final CatDrawer category) {

            Glide.with(context).load(category.getImage()).into(imageView);


            textView.setText(category.getName());
            itemView.setOnClickListener(v -> {

                if (mClickListener != null) {
                    mClickListener.onCategoryItemClick(category, getAdapterPosition());
//                    Toast.makeText(context,category.getId(),Toast.LENGTH_SHORT).show();
                }});
        }
    }
}

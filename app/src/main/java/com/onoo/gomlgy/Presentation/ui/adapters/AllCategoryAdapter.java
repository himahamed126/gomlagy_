package com.onoo.gomlgy.Presentation.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.Models.SubCategorymodel;
import com.onoo.gomlgy.Presentation.ui.listeners.AllCategoryClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Utils.HelperMethod;
import com.onoo.gomlgy.Utils.ItemAnimation;

import java.util.List;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.ViewHolder> {
    int selected_position = 0;
    private Context context;
    private List<SubCategorymodel> mCategories;
    private LayoutInflater mInflater;
    private AllCategoryClickListener mClickListener;
    int selectedPosition=-1;
    private int lastPosition = -1;
    private boolean on_attach = true;

    public AllCategoryAdapter(Context context, List<SubCategorymodel> categories, AllCategoryClickListener listener) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mCategories = categories;
        this.mClickListener = listener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.category_list_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mCategories.get(position));
        holder.itemView.setBackgroundColor(selected_position == position ? Color.parseColor("#DCDCDC") : Color.WHITE);
//        HelperMethod.setAnimation(holder.itemView, position, ItemAnimation.BOTTOM_UP, lastPosition, on_attach);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        ImageView imageView;
        TextView textView;
        ViewHolder(View itemView) {
            super(itemView);
        //    imageView = itemView.findViewById(R.id.category_icon);
            textView = itemView.findViewById(R.id.category_name);
            itemView.setOnClickListener(this);
        }

        public void bind(final SubCategorymodel category) {
           // Glide.with(context).load(AppConfig.ASSET_URL + category.getIcon()).into(imageView);
            textView.setText(category.getName());

        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            notifyItemChanged(selected_position);
            if (mClickListener != null) mClickListener.onCategoryClick(mCategories.get(getAdapterPosition()));
        }
    }
}

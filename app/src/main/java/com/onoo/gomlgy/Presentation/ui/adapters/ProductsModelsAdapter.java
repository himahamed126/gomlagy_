package com.onoo.gomlgy.Presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.Presentation.ui.listeners.ProductModelsClickListener;
import com.onoo.gomlgy.R;

import java.util.List;

public class ProductsModelsAdapter extends RecyclerView.Adapter<ProductsModelsAdapter.ViewHolder> {

    private List<String> models;
    private LayoutInflater mInflater;
    private Context context;
    private ProductModelsClickListener productClickListener;

    int rowIndex = -1;

    // data is passed into the constructor
    public ProductsModelsAdapter(Context context, List<String> models, ProductModelsClickListener productClickListener) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.models = models;
        this.productClickListener = productClickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.product_model_box, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(models.get(position));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return models.size();
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return models.get(id);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ConstraintLayout constraintLayout;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.model_name);
            constraintLayout = itemView.findViewById(R.id.model_container);
        }

        public void bind(String options) {

            if (rowIndex == getAdapterPosition()) {
                constraintLayout.setBackground(context.getDrawable(R.drawable.sh_selected_model));
            } else {
                constraintLayout.setBackground(context.getDrawable(R.drawable.sh_no_select_model));
            }
            name.setText(options);
            itemView.setOnClickListener(view -> {
                rowIndex = getAdapterPosition();
                notifyDataSetChanged();
                productClickListener.onModelsItemClick(options);
            });
        }
    }
}


package com.onoo.gomlgy.Presentation.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.Presentation.ui.listeners.WarrantyListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.databinding.ItemWarrantyBinding;
import com.onoo.gomlgy.models.WarrantyData;

import java.util.ArrayList;
import java.util.List;

public class WarrantyAdapter extends RecyclerView.Adapter<WarrantyAdapter.WarrantyViewHolder> {

    List<WarrantyData> items = new ArrayList<>();
    WarrantyListener warrantyListener;

    @NonNull
    @Override
    public WarrantyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemWarrantyBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_warranty, parent, false);
        return new WarrantyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WarrantyViewHolder holder, int position) {
        WarrantyData warrantyData = items.get(position);
        holder.binding.setWarranty(warrantyData);

        holder.binding.itemWarrantyToolbar.inflateMenu(R.menu.warranty_menu);
        holder.binding.itemWarrantyToolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.edit:
                    warrantyListener.onItemMenuClickListener(warrantyData, 1);
                    break;
                case R.id.delete:
                    warrantyListener.onItemMenuClickListener(warrantyData, 2);
                    break;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<WarrantyData> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    class WarrantyViewHolder extends RecyclerView.ViewHolder {
        private ItemWarrantyBinding binding;

        public WarrantyViewHolder(ItemWarrantyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setOnMenuClickListener(WarrantyListener warrantyListener) {
        this.warrantyListener = warrantyListener;
    }

    public void deleteItem(int warrantyId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == warrantyId) {
                items.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, items.size());
            }
        }
    }
}

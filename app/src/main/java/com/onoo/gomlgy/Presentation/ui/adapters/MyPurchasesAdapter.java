package com.onoo.gomlgy.Presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.Presentation.ui.listeners.MyPurchasesListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.databinding.ItemMyPurchasesBinding;
import com.onoo.gomlgy.models.MyPurchasesData;

import java.util.ArrayList;
import java.util.List;

public class MyPurchasesAdapter extends RecyclerView.Adapter<MyPurchasesAdapter.WarrantyViewHolder> {

    private List<MyPurchasesData> items = new ArrayList<>();
    private MyPurchasesListener myPurchasesListener;
    private int rowIndex = -1;
    private Context context;
    private int lastPosition;
    private int selectId;

    private static final String TAG = "MyPurchasesAdapter";

    public MyPurchasesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WarrantyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMyPurchasesBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_my_purchases, parent, false);
        return new WarrantyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WarrantyViewHolder holder, int position) {
        holder.bind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<MyPurchasesData> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setSelectedItem(int id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                selectId = id;
                notifyDataSetChanged();
            }
        }
    }

    class WarrantyViewHolder extends RecyclerView.ViewHolder {
        private ItemMyPurchasesBinding binding;

        public WarrantyViewHolder(ItemMyPurchasesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(MyPurchasesData purchasesData, int pos) {
            binding.setMyPurchasesData(purchasesData);
            binding.executePendingBindings();
            binding.myPurchasesCheckbox.setChecked(rowIndex == pos);

            if (purchasesData.getId() == selectId) {
                binding.parentLyt.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                binding.myPurchasesNameTv.setTextColor(context.getResources().getColor(R.color.white));
                binding.myPurchasesCodeTv.setTextColor(context.getResources().getColor(R.color.white));
                binding.myPurchasesCheckbox.setChecked(true);
                lastPosition = pos;
                selectId = -1;
            } else if (rowIndex == pos) {
                binding.parentLyt.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                binding.myPurchasesNameTv.setTextColor(context.getResources().getColor(R.color.white));
                binding.myPurchasesCodeTv.setTextColor(context.getResources().getColor(R.color.white));
                lastPosition = pos;
            } else {
                binding.parentLyt.setBackgroundColor(context.getResources().getColor(R.color.white));
                binding.myPurchasesNameTv.setTextColor(context.getResources().getColor(R.color.black));
                binding.myPurchasesCodeTv.setTextColor(context.getResources().getColor(R.color.black));
            }

            binding.getRoot().setOnClickListener(v -> {
                rowIndex = pos;
                notifyItemChanged(lastPosition);
                notifyItemChanged(pos);
                myPurchasesListener.onItemPurchasesClickListener(purchasesData, pos);
            });
        }
    }

    public void setOnPurchasesClickListener(MyPurchasesListener myPurchasesListener) {
        this.myPurchasesListener = myPurchasesListener;
    }
}

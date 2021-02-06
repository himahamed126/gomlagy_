package com.onoo.gomlgy.Presentation.ui.adapters;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.onoo.gomlgy.R;
import com.onoo.gomlgy.databinding.ItemBuyDataBinding;
import com.onoo.gomlgy.databinding.ItemBuyDataHeaderBinding;
import com.onoo.gomlgy.models.BuyDataModel;

import java.util.ArrayList;
import java.util.List;

public class BuyDataAdapter extends RecyclerView.Adapter<BuyDataAdapter.ViewModel> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    OnQuantityChanged onQuantityChanged;

    List<BuyDataModel> items = new ArrayList<>();

    private static final String TAG = "BuyDataAdapter";

    BuyDataModel model;
    double firstPrice;

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewBinding binding;

        if (viewType == TYPE_HEADER) {
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_buy_data_header, parent, false);
            return new ViewModel((ItemBuyDataHeaderBinding) binding);
        } else if (viewType == TYPE_ITEM) {
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_buy_data, parent, false);
            return new ViewModel((ItemBuyDataBinding) binding);
        } else return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel holder, int position) {
        if (holder.getItemViewType() == TYPE_ITEM) {
            ItemBuyDataBinding binding = holder.itemBinding;
            model = items.get(position);
            binding.setModel(model);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    class ViewModel extends RecyclerView.ViewHolder {
        ItemBuyDataHeaderBinding headerBinding;
        ItemBuyDataBinding itemBinding;

        public ViewModel(ItemBuyDataHeaderBinding headerBinding) {
            super(headerBinding.getRoot());
            this.headerBinding = headerBinding;
        }

        public ViewModel(ItemBuyDataBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;


            itemBinding.quantityEd.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence value, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence value, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable value) {
                    firstPrice = items.get(getAdapterPosition()).getPrice();

                    if (value.length() > 0 && TextUtils.isDigitsOnly(value)) {
                        int quantity = Integer.parseInt(value.toString());
                        double newPrice = (int) (firstPrice * quantity);
                        model.setChangePrice(newPrice);
                        model.setChangeQuantity(quantity);
                        Log.i(TAG, " : firstPrice : " + firstPrice);
                        Log.i(TAG, " : quantity : " + model.getQuantity());
                        Log.i(TAG, " : price : " + model.getChangePrice());
                        Log.i(TAG, " : value : " + value.toString());
                        Log.i(TAG, " : Position : " + getAdapterPosition());
                        notifyItemChanged(getAdapterPosition());
                    }
                }
            });
        }
    }

    public void setItems(List<BuyDataModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }


    public void onQuantityChangedFun(OnQuantityChanged onQuantityChanged) {
        this.onQuantityChanged = onQuantityChanged;
    }

    public void changePrice(int position, double newPrice, int quantity) {
        BuyDataModel model = items.get(position);

        model.setId(model.getId());
        model.setColor(model.getColor());
        model.setModelName(model.getModelName());
        model.setQuantity(quantity);
        model.setPrice(newPrice);
        notifyItemChanged(position);
    }

    public interface OnQuantityChanged {
        void onItemQuantityChanged(int position, int quantity);
    }
}

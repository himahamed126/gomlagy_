package com.onoo.gomlgy.Presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.Presentation.ui.listeners.ProductClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.databinding.ItemProductHorBinding;
import com.onoo.gomlgy.databinding.ItemProductGridBinding;
import com.onoo.gomlgy.databinding.ItemProductVerBinding;
import com.onoo.gomlgy.models.Product;

import java.util.List;

import static com.onoo.gomlgy.Utils.AppConfig.calcLessPrice;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private final List<Product> mProducts;
    private LayoutInflater layoutInflater;
    private final ProductClickListener productClickListener;
    private final Context context;
    int TYPE;

    private static final int HOR_TYPE = 1, VIR_TYPE = 2, GRID_TYPE = 3;

    public ProductsAdapter(Context context, List<Product> mProducts, ProductClickListener productClickListener) {
        this.context = context;
        this.mProducts = mProducts;
        this.productClickListener = productClickListener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding;

        switch (viewType) {
            case HOR_TYPE:
                binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_product_hor, parent, false);
                return new ViewHolder((ItemProductHorBinding) binding);
            case VIR_TYPE:
                binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_product_ver, parent, false);
                return new ViewHolder((ItemProductVerBinding) binding);
            default:
                binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_product_grid, parent, false);
                return new ViewHolder((ItemProductGridBinding) binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {
        Product product = mProducts.get(position);
        double p1 = product.getUnitPrice(), p2 = product.getUnitPrice2(), p3 = product.getUnitPrice3();

        switch (holder.getItemViewType()) {
            case GRID_TYPE:
                ItemProductGridBinding gridBinding = holder.gridBinding;
                calcLessPrice(p1, p2, p3, gridBinding.price1, gridBinding.price2, gridBinding.price3, context);
                gridBinding.setProduct(product);
                gridBinding.productCv.setOnClickListener(view -> productClickListener.onProductItemClick(product));
                break;
            case HOR_TYPE:
                ItemProductHorBinding horBinding = holder.horBinding;
                calcLessPrice(p1, p2, p3, horBinding.price1, horBinding.price2, horBinding.price3, context);
                horBinding.setProduct(product);
                horBinding.productCv.setOnClickListener(view -> productClickListener.onProductItemClick(product));
                break;
            case VIR_TYPE:
                ItemProductVerBinding verBinding = holder.verBinding;
                calcLessPrice(p1, p2, p3, verBinding.price1, verBinding.price2, verBinding.price3, context);
                verBinding.setProduct(product);
                verBinding.productCv.setOnClickListener(view -> productClickListener.onProductItemClick(product));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (TYPE) {
            case 1:
                return HOR_TYPE;
            case 2:
                return VIR_TYPE;
            default:
                return GRID_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void setViewType(int type) {
        this.TYPE = type;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemProductGridBinding gridBinding;
        private ItemProductHorBinding horBinding;
        private ItemProductVerBinding verBinding;

        ViewHolder(ItemProductGridBinding binding) {
            super(binding.getRoot());
            this.gridBinding = binding;
        }

        ViewHolder(ItemProductHorBinding binding) {
            super(binding.getRoot());
            this.horBinding = binding;
        }

        ViewHolder(ItemProductVerBinding binding) {
            super(binding.getRoot());
            this.verBinding = binding;
        }
    }
}

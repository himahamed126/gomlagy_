package com.onoo.gomlgy.Presentation.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.Presentation.ui.activities.impl.PaymentActivity.PaymentModel;
import com.onoo.gomlgy.Presentation.ui.listeners.PaymentSelectListener;
import com.onoo.gomlgy.R;

import java.util.List;

public class PaymentSelectAdapter extends RecyclerView.Adapter<PaymentSelectAdapter.ViewHolder> {

    private Context context;
    private List<PaymentModel> paymentModels;
    private LayoutInflater mInflater;
    private PaymentSelectListener paymentSelectListener;
    private int row_index = -1;

    // data is passed into the constructor
    public PaymentSelectAdapter(Context context, List<PaymentModel> paymentModels, PaymentSelectListener paymentSelectListener) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.paymentModels = paymentModels;
        this.paymentSelectListener = paymentSelectListener;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.payment_select_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(paymentModels.get(position), position);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return paymentModels.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView payment_icon;
        TextView payment_text;
        RelativeLayout payment_layout;
        CheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            payment_icon = itemView.findViewById(R.id.payment_icon);
            payment_text = itemView.findViewById(R.id.payment_text);
            payment_layout = itemView.findViewById(R.id.payment_layout);
            checkBox = itemView.findViewById(R.id.checkbox_payment);
        }

        public void bind(final PaymentModel paymentModel, int position) {
            payment_icon.setImageResource(paymentModel.getDrawable());
            payment_text.setText(paymentModel.getPayment_text());
            checkBox.setChecked(row_index == position);

            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    row_index = position;
                    notifyDataSetChanged();
                    if (paymentSelectListener != null) {
                        paymentSelectListener.onPaymentItemClick(paymentModel);
                    }
                }
            });
        }
    }
}


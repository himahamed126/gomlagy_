package com.onoo.gomlgy.Presentation.ui.binding_adapter;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.onoo.gomlgy.R;

public class GoBindingAdapter {

    @BindingAdapter("setStringText")
    public static void setStringText(TextView textView, Object value) {
        textView.setText(String.valueOf(value));
    }

    @BindingAdapter("adminApproval")
    public static void setAdminApproval(TextView textView, int value) {
        if (value == 0) {
            textView.setText(textView.getContext().getString(R.string.unconfirmed));
        } else {
            textView.setText(textView.getContext().getString(R.string.confirmed));
        }

    }

    @BindingAdapter("setBgColor")
    public static void setBgColor(ImageView imageView, String color) {
        imageView.setBackgroundColor(Color.parseColor(color));
    }
}

package com.onoo.gomlgy.Presentation.ui.activities.impl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.onoo.gomlgy.Presentation.ui.adapters.BuyDataAdapter;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.databinding.ActivityBuyDataBinding;
import com.onoo.gomlgy.models.BuyDataModel;

import java.util.ArrayList;
import java.util.List;

public class BuyDataActivity extends AppCompatActivity implements BuyDataAdapter.OnQuantityChanged {

    private List<BuyDataModel> items = new ArrayList<>();
    private ActivityBuyDataBinding binding;
    private BuyDataAdapter buyDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_buy_data);
        getSupportActionBar().hide();
        init();
    }


    void init() {
        items.add(new BuyDataModel(1, "#E91E63", "x1", 1, 1, 100, 100));
        items.add(new BuyDataModel(2, "#E91E63", "x1", 1, 1, 100, 100));
        items.add(new BuyDataModel(3, "#00BCD4", "x2", 1, 1, 200, 200));
        items.add(new BuyDataModel(4, "#4CAF50", "x3", 1, 1, 300, 300));

        buyDataAdapter = new BuyDataAdapter();
        buyDataAdapter.setItems(items);
        binding.buyDataRv.setAdapter(buyDataAdapter);
        binding.buyDataRv.setLayoutManager(new LinearLayoutManager(this));


        buyDataAdapter.onQuantityChangedFun(this);
    }

    @Override
    public void onItemQuantityChanged(int position, int quantity) {
        double newPrice = (int) (items.get(position).getPrice() * quantity);
        buyDataAdapter.changePrice(position, newPrice, quantity);
    }
}
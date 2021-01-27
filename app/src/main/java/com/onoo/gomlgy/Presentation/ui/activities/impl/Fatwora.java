package com.onoo.gomlgy.Presentation.ui.activities.impl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.onoo.gomlgy.Network.response.AuthResponse;
import com.onoo.gomlgy.Network.response.CartQuantityUpdateResponse;
import com.onoo.gomlgy.Network.response.RemoveCartResponse;
import com.onoo.gomlgy.Presentation.presenters.CartPresenter;
import com.onoo.gomlgy.Presentation.ui.adapters.CartListAdapter;
import com.onoo.gomlgy.Presentation.ui.adapters.FatworaAdapter;
import com.onoo.gomlgy.Presentation.ui.fragments.CartView;
import com.onoo.gomlgy.Presentation.ui.listeners.CartItemListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.UserPrefs;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.onoo.gomlgy.models.CartModel;
import com.onoo.gomlgy.models.ShippingAddress;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Fatwora extends AppCompatActivity implements CartView, CartItemListener {
    private CartPresenter cartPresenter;
    private AuthResponse authResponse;
    UserPrefs user;
    TextView total_txt, shippinAddresstxt, shippingCosttxt, orderDate;
    private Double total = 0.0, shipping = 0.0, tax = 0.0, coupon_discount = 0.0;
    private String shippingAddress, address, citytxt, countrytxt;
    private TableLayout mTableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatwora);
        mTableLayout = (TableLayout) findViewById(R.id.tableInvoices);
        shippinAddresstxt = findViewById(R.id.orderAddresstxt);
        orderDate = findViewById(R.id.orderDate);
        total_txt=findViewById(R.id.totalAmount);
        shippingCosttxt=findViewById(R.id.shippint_cost);

        total = getIntent().getDoubleExtra("total", 0.0);
        shipping = getIntent().getDoubleExtra("shipping", 0.0);
        citytxt = getIntent().getStringExtra("city");
        countrytxt = getIntent().getStringExtra("country");
        address = getIntent().getStringExtra("address");
        tax = getIntent().getDoubleExtra("tax", 0.0);
        shippingAddress = getIntent().getStringExtra("shipping_address");
        user = new UserPrefs(Fatwora.this);
        cartPresenter = new CartPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        authResponse = user.getAuthPreferenceObjectJson("auth_response");
        if (authResponse != null && authResponse.getUser() != null) {
            cartPresenter.getCartItems(authResponse.getUser().getId(), authResponse.getAccessToken());
        }
        if (shippingAddress != null) {
            try {
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                orderDate.setText(currentDate);
                JSONObject jsonObj = new JSONObject(getIntent().getStringExtra("shipping_address"));
                shippinAddresstxt.setText(jsonObj.get("address").toString() + "," + jsonObj.get("city").toString() + "," + jsonObj.get("country").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void setCartItems(List<CartModel> cartItems) {
        initializeTableLayout();
        fillTable(cartItems);
        setShipingCost(cartItems);
        total_txt.setText(total.toString());
    }

    private void initializeTableLayout() {

        TableRow tr_head = new TableRow(Fatwora.this);
        tr_head.setId(View.generateViewId());
        tr_head.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        tr_head.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        TableRow tableRow = new TableRow(this);

        TextView label_barcode = new TextView(Fatwora.this);

        label_barcode.setId(View.generateViewId());
        label_barcode.setText(R.string.product_name);
        label_barcode.setTextSize(20);
        label_barcode.setTextColor(Color.BLACK);
        label_barcode.setGravity(Gravity.CENTER);
        tr_head.addView(label_barcode);// add the column to the table row here

        TextView label_location = new TextView(Fatwora.this);
        label_location.setId(View.generateViewId());// define id that must be         unique
        label_location.setText(R.string.price); // set the text for the header
        label_location.setTextSize(20);
        label_location.setTextColor(Color.BLACK); // set the color
        label_location.setGravity(Gravity.CENTER);
        tr_head.addView(label_location); // add the column to the table row here

        TextView label_shipping = new TextView(Fatwora.this);
        label_shipping.setId(View.generateViewId());// define id that must be         unique
        label_shipping.setText(R.string.shipping); // set the text for the header
        label_shipping.setTextSize(20);
        label_shipping.setTextColor(Color.BLACK); // set the color
        label_shipping.setGravity(Gravity.CENTER);
        tr_head.addView(label_shipping);

        TextView label_quantity = new TextView(Fatwora.this);
        label_quantity.setId(View.generateViewId());// define id that must be         unique
        label_quantity.setText(R.string.quantity); // set the text for the header
        label_quantity.setTextSize(20);
        label_quantity.setTextColor(Color.BLACK); // set the color
        label_quantity.setGravity(Gravity.CENTER);
        tr_head.addView(label_quantity); //
        mTableLayout.setScrollContainer(true);
        mTableLayout.addView(tr_head, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    }
    private void fillTable(List<CartModel> cartItems) {
        Integer count = 0;
        for (int i = 0; i < cartItems.size(); i++) {
            TableRow tableRow = new TableRow(Fatwora.this);
            if (count % 2 != 0) {
                tableRow.setBackgroundColor(getResources().getColor(R.color.carbon_grey_100));
            }
            tableRow.setId(View.generateViewId());
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

            TextView labelBarcode = new TextView(Fatwora.this);
            labelBarcode.setId(View.generateViewId());
            labelBarcode.setGravity(Gravity.CENTER);
            labelBarcode.setTextColor(getResources().getColor(R.color.colorAccent));
            labelBarcode.setTextSize(18);
//            labelBarcode.setText();
           labelBarcode.setText(cartItems.get(i).getProduct().getName());

            tableRow.addView(labelBarcode);

            TextView labelLocation = new TextView(Fatwora.this);
            labelLocation.setId(View.generateViewId());
            labelLocation.setGravity(Gravity.CENTER);
            labelLocation.setTextColor(getResources().getColor(R.color.colorAccent));
            labelLocation.setTextSize(18);
            labelLocation.setText(cartItems.get(i).getPrice().toString());
            tableRow.addView(labelLocation);

            TextView labelQuantity = new TextView(Fatwora.this);
            labelQuantity.setId(View.generateViewId());
            labelQuantity.setGravity(Gravity.CENTER);
            labelQuantity.setTextColor(getResources().getColor(R.color.colorAccent));
            labelQuantity.setTextSize(18);
            labelQuantity.setText(cartItems.get(i).getShippingCost().toString());
            tableRow.addView(labelQuantity);

            TextView labelQuantityy = new TextView(Fatwora.this);
            labelQuantityy.setId(View.generateViewId());
            labelQuantityy.setGravity(Gravity.CENTER);
            labelQuantityy.setTextColor(getResources().getColor(R.color.colorAccent));
            labelQuantityy.setTextSize(18);
            labelQuantityy.setText(cartItems.get(i).getQuantity().toString());
            tableRow.addView(labelQuantityy);
            mTableLayout.addView(tableRow, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            count++;
        }
    }
    private void setShipingCost(List<CartModel> cartItems) {
        double sum = 0;
        for (int i = 0; i < cartItems.size(); i++) {
            sum += cartItems.get(i).getShippingCost();
        }
        shippingCosttxt.setText(String.valueOf(sum));
    }

    @Override
    public void showRemoveCartMessage(RemoveCartResponse removeCartResponse) {

    }

    @Override
    public void showCartQuantityUpdateMessage(CartQuantityUpdateResponse cartQuantityUpdateResponse) {

    }

    @Override
    public void onCartRemove(CartModel cartModel) {

    }

    @Override
    public void onQuantityUpdate(int quantity, CartModel cartModel) {

    }

    public void CheckOut(View view) {
        try {
            JSONObject jsonObj = new JSONObject(getIntent().getStringExtra("shipping_address"));
            Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
            intent.putExtra("total", total);
            intent.putExtra("shipping", shipping);
            intent.putExtra("tax", tax);
            intent.putExtra("shipping_address", jsonObj.toString());
            startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
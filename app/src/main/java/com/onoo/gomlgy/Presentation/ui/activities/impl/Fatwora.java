package com.onoo.gomlgy.Presentation.ui.activities.impl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.List;

public class Fatwora extends AppCompatActivity implements CartView, CartItemListener {
    private CartPresenter cartPresenter;
    private AuthResponse authResponse;
    UserPrefs user;
    TextView total_txt, shippinAddress, tax_txt, shippingCosttxt,city,country;
    private Double total = 0.0, shipping = 0.0, tax = 0.0, coupon_discount = 0.0;
    private String shippingAddress,address,citytxt,countrytxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatwora);
        shippingCosttxt = findViewById(R.id.total_shipping_cost);
        tax_txt = findViewById(R.id.tax_cost);
        shippinAddress = findViewById(R.id.shipping_address);
        city=findViewById(R.id.city_txt);
        country=findViewById(R.id.country_txt);
        total_txt = findViewById(R.id.total_amount);
        total = getIntent().getDoubleExtra("total", 0.0);
        shipping = getIntent().getDoubleExtra("shipping", 0.0);
        citytxt=getIntent().getStringExtra("city");
        countrytxt=getIntent().getStringExtra("country");
        address=getIntent().getStringExtra("address");

        tax = getIntent().getDoubleExtra("tax", 0.0);
        shippingAddress = getIntent().getStringExtra("shipping_address");
        user = new UserPrefs(Fatwora.this);
        cartPresenter = new CartPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        authResponse = user.getAuthPreferenceObjectJson("auth_response");
        if (authResponse != null && authResponse.getUser() != null) {
            cartPresenter.getCartItems(authResponse.getUser().getId(), authResponse.getAccessToken());
        }
        if (shippingAddress != null && total != null && shipping != null && tax != null) {
            total_txt.setText(total.toString());
            try {
                JSONObject jsonObj = new JSONObject(getIntent().getStringExtra("shipping_address"));
                city.setText(jsonObj.get("city").toString());
                country.setText(jsonObj.get("country").toString());
                shippinAddress.setText(jsonObj.get("address").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            tax_txt.setText(tax.toString());
        }
    }


    @Override
    public void setCartItems(List<CartModel> cartItems) {
        if (cartItems.size() > 0) {
            RecyclerView recyclerView = findViewById(R.id.fatworaproduct_list);
            LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(Fatwora.this);
            recyclerView.setLayoutManager(horizontalLayoutManager);
            FatworaAdapter adapter = new FatworaAdapter(Fatwora.this, cartItems, this);
            recyclerView.setAdapter(adapter);
            setShipingCost(cartItems);
        }
    }

    private void setShipingCost(List<CartModel> cartItems) {
        double sum=0;
for(int i=0;i<cartItems.size();i++)
{
sum+=cartItems.get(i).getShippingCost();
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
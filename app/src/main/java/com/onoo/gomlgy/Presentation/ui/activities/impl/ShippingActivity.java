package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.models.ShippingAddress;
import com.onoo.gomlgy.models.User;
import com.onoo.gomlgy.Network.response.AuthResponse;
import com.onoo.gomlgy.Network.response.ProfileInfoUpdateResponse;
import com.onoo.gomlgy.Network.response.ShippingInfoResponse;
import com.onoo.gomlgy.Presentation.presenters.AccountInfoPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.AccountInfoView;
import com.onoo.gomlgy.Presentation.ui.adapters.ShippingAddressSelectAdapter;
import com.onoo.gomlgy.Presentation.ui.listeners.ShippingAddressSelectListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.AppConfig;
import com.onoo.gomlgy.Utils.CustomToast;
import com.onoo.gomlgy.Utils.UserPrefs;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.google.gson.JsonObject;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.ArrayList;
import java.util.List;

public class ShippingActivity extends BaseActivity implements AccountInfoView, ShippingAddressSelectListener {
    private AuthResponse authResponse;
    private Button payment;
    private Double total = 0.0, shipping = 0.0, tax = 0.0;
    private RecyclerView recyclerView;
    private ShippingAddress shippingAddress = null;
    List<ShippingAddress> shippingAddresses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);
        total = getIntent().getDoubleExtra("total", 0.0);
        shipping = getIntent().getDoubleExtra("shipping", 0.0);
        tax = getIntent().getDoubleExtra("tax", 0.0);
        initializeActionBar();
        setTitle(getString(R.string.shipping_nformation));
        initviews();
        authResponse = new UserPrefs(this).getAuthPreferenceObjectJson("auth_response");
        if (authResponse != null && authResponse.getUser() != null) {
            new AccountInfoPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).getShippingAddresses(authResponse.getUser().getId(), authResponse.getAccessToken());
        }
    }
    private void initviews() {
        recyclerView = findViewById(R.id.rv_shipping_addresses);
        payment = findViewById(R.id.payment);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!shippingAddresses.isEmpty()) {
                    if (shippingAddress != null) {
                       //Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);

                       Intent intent = new Intent(getApplicationContext(), Fatwora.class);
                        intent.putExtra("total", total);
                        intent.putExtra("shipping", shipping);
                        intent.putExtra("tax", tax);
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("name", authResponse.getUser().getName());
                        jsonObject.addProperty("email", authResponse.getUser().getEmail());
                        jsonObject.addProperty("address", shippingAddress.getAddress());
                        jsonObject.addProperty("country", shippingAddress.getCountry());
                        jsonObject.addProperty("city", shippingAddress.getCity());
                        jsonObject.addProperty("postal_code", shippingAddress.getPostalCode());
                        jsonObject.addProperty("phone", shippingAddress.getPhone());
                        jsonObject.addProperty("checkout_type", "logged");
                        intent.putExtra("shipping_address", jsonObject.toString());
                        startActivity(intent);





                    } else {
                        CustomToast.showToast(ShippingActivity.this, getString(R.string.please_choose_shipping_address), R.color.colorWarning);
                    }
                } else {
                    CustomToast.showToast(ShippingActivity.this, getString(R.string.please_choose_shipping_address), R.color.colorWarning);
                    startActivity(new Intent(ShippingActivity.this, AccountInfoActivity.class));
                }
            }
        });
    }

    @Override
    public void onProfileInfoUpdated(ProfileInfoUpdateResponse profileInfoUpdateResponse) {

    }

    @Override
    public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
        recyclerView.addItemDecoration(new LayoutMarginDecoration(1, AppConfig.convertDpToPx(getApplicationContext(), 10)));
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        this.shippingAddresses.addAll(shippingAddresses);
        ShippingAddressSelectAdapter adapter = new ShippingAddressSelectAdapter(getApplicationContext(), this.shippingAddresses, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onShippingInfoCreated(ShippingInfoResponse shippingInfoResponse) {

    }

    @Override
    public void onShippingInfoDeleted(ShippingInfoResponse shippingInfoResponse) {

    }

    @Override
    public void setUpdatedUserInfo(User user) {

    }

    @Override
    public void onShippingAddressItemClick(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}

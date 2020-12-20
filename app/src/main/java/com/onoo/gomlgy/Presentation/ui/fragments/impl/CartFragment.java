package com.onoo.gomlgy.Presentation.ui.fragments.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.onoo.gomlgy.Models.CartModel;
import com.onoo.gomlgy.Network.response.AuthResponse;
import com.onoo.gomlgy.Network.response.CartQuantityUpdateResponse;
import com.onoo.gomlgy.Network.response.RemoveCartResponse;
import com.onoo.gomlgy.Presentation.presenters.CartPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.impl.ShippingActivity;
import com.onoo.gomlgy.Presentation.ui.adapters.CartListAdapter;
import com.onoo.gomlgy.Presentation.ui.fragments.CartView;
import com.onoo.gomlgy.Presentation.ui.listeners.CartItemListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.AppConfig;
import com.onoo.gomlgy.Utils.CustomToast;
import com.onoo.gomlgy.Utils.SwipeToDeleteCallback;
import com.onoo.gomlgy.Utils.UserPrefs;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;

import java.util.List;

import q.rorbin.badgeview.QBadgeView;

//import static com.onoo.gomlgy.Presentation.ui.activities.impl.MainActivity.navView;

public class CartFragment extends Fragment implements CartView, CartItemListener {
    private View v;
    private CartPresenter cartPresenter;
    private AuthResponse authResponse;
    private Button btnCheckout;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;
    private TextView total_amount;
    private double total = 0;
    private double shipping = 0;
    private double tax = 0;
    private int qty = 0;
    private TextView cart_empty_text;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_cart, null);

        btnCheckout = v.findViewById(R.id.checkout);
        progressBar = v.findViewById(R.id.item_progress_bar);
        linearLayout = v.findViewById(R.id.checkout_button);
        total_amount = v.findViewById(R.id.total_amount);
        cart_empty_text = v.findViewById(R.id.cart_empty_text);

        linearLayout.setVisibility(View.GONE);

        cartPresenter = new CartPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);

        authResponse = new UserPrefs(getActivity()).getAuthPreferenceObjectJson("auth_response");
        if(authResponse != null && authResponse.getUser() != null){
            cartPresenter.getCartItems(authResponse.getUser().getId(), authResponse.getAccessToken());
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            cart_empty_text.setVisibility(View.VISIBLE);
        }

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShippingActivity.class);
                intent.putExtra("total", total);
                intent.putExtra("shipping", shipping);
                intent.putExtra("tax", tax);
                startActivity(intent);
            }
        });

        return v;
    }

    private void updateCartBadge(List<CartModel> cartItems){
        if (cartItems.size() > 0){
            linearLayout.setVisibility(View.VISIBLE);
            total = 0;
            qty = 0;

            for (CartModel cartModel: cartItems){
                total += (cartModel.getPrice()+cartModel.getTax()+cartModel.getShippingCost())*cartModel.getQuantity();
                shipping = cartModel.getShippingCost()*cartModel.getQuantity();
                tax = cartModel.getTax()+cartModel.getQuantity();
                qty += cartModel.getQuantity();
            }

            total_amount.setText(AppConfig.convertPrice(getContext(), total));

//            BottomNavigationMenuView bottomNavigationMenuView =
//                    (BottomNavigationMenuView) navView.getChildAt(0);
//            View v = bottomNavigationMenuView.getChildAt(3); // number of menu from left
            new QBadgeView(getActivity()).bindTarget(v).setBadgeText(String.valueOf(qty)).setShowShadow(false);
        }
    }

    @Override
    public void setCartItems(List<CartModel> cartItems) {
        progressBar.setVisibility(View.GONE);
        if (cartItems.size() > 0){
            RecyclerView recyclerView = v.findViewById(R.id.product_list);
            LinearLayoutManager horizontalLayoutManager
                    = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(horizontalLayoutManager);
            CartListAdapter adapter = new CartListAdapter(getActivity(), cartItems, this);
            recyclerView.setAdapter(adapter);

            ItemTouchHelper itemTouchHelper = new
                    ItemTouchHelper(new SwipeToDeleteCallback(adapter));
            itemTouchHelper.attachToRecyclerView(recyclerView);

            updateCartBadge(cartItems);
        }
        else {
            cart_empty_text.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showRemoveCartMessage(RemoveCartResponse removeCartResponse) {
        CustomToast.showToast(getActivity(), removeCartResponse.getMessage(), R.color.colorSuccess);
        cartPresenter.getCartItems(authResponse.getUser().getId(), authResponse.getAccessToken());
    }

    @Override
    public void showCartQuantityUpdateMessage(CartQuantityUpdateResponse cartQuantityUpdateResponse) {
        CustomToast.showToast(getActivity(), cartQuantityUpdateResponse.getMessage(), R.color.colorSuccess);
        cartPresenter.getCartItems(authResponse.getUser().getId(), authResponse.getAccessToken());
    }

    @Override
    public void onCartRemove(CartModel cartModel) {
        cartPresenter.removeCartItem(cartModel.getId(), authResponse.getAccessToken());
    }

    @Override
    public void onQuantityUpdate(int qty, CartModel cartModel) {
        cartPresenter.updateCartQuantity(cartModel.getId(), qty, authResponse.getAccessToken());
    }
}

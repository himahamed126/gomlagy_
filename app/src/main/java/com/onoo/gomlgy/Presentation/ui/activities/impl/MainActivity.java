package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.onoo.gomlgy.Models.SubCategorymodel;
import com.onoo.gomlgy.Models.collectionmodel;
import com.onoo.gomlgy.Network.ApiClient;
import com.onoo.gomlgy.Network.response.AppSettingsResponse;
import com.onoo.gomlgy.Network.services.getProductsWithSubcategory;
import com.onoo.gomlgy.Presentation.ui.adapters.DrawerCategoryAdapter;
import com.onoo.gomlgy.Presentation.ui.fragments.impl.AccountFragment;
import com.onoo.gomlgy.Presentation.ui.fragments.impl.CartFragment;
import com.onoo.gomlgy.Presentation.ui.fragments.impl.HomeFragment;
import com.onoo.gomlgy.Presentation.ui.fragments.impl.ProductSearchFragment;
import com.onoo.gomlgy.Presentation.ui.fragments.impl.twofragments;
import com.onoo.gomlgy.Presentation.ui.listeners.DrawerClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.CustomToast;
import com.onoo.gomlgy.Utils.HelperMethod;
import com.onoo.gomlgy.Utils.UserPrefs;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.onoo.gomlgy.domain.interactors.AppSettingsInteractor;
import com.onoo.gomlgy.domain.interactors.impl.AppSettingsInteractorImpl;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AppSettingsInteractor.CallBack, DrawerClickListener {

    final Fragment homeFragment = new HomeFragment();
    //   final Fragment categoriesFragment = new CategoriesFragment();TwoFragmentsDetails

    final Fragment categoriesFragment = new twofragments();

    private Fragment cartFragment = new CartFragment();
    private Fragment accountFragment = new AccountFragment();
    private Fragment searchFragment = new ProductSearchFragment();
    final FragmentManager fm = getSupportFragmentManager();
    private Fragment active = homeFragment;
    MeowBottomNavigation meo;
    private ImageView cartIv, search, clearIv;
    EditText searchEt;

    boolean focus = false;
    int tabId = 1;
    getProductsWithSubcategory apiService;
    private List<SubCategorymodel> mCategories = new ArrayList<>();
    RecyclerView drawerRv;
    TextView name, email;

    private Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;

    void initBottom() {
        meo = findViewById(R.id.bottomNavigation);
        meo.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home_black_24dp));
        meo.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_view_module_24));
        meo.add(new MeowBottomNavigation.Model(3, R.drawable.ic_search));
        meo.add(new MeowBottomNavigation.Model(4, R.drawable.ic_shopping_cart));
        meo.add(new MeowBottomNavigation.Model(5, R.drawable.ic_account_circle_black_24dp));


        meo.setOnClickMenuListener(item -> {
            tabId = item.getId();
            switch (item.getId()) {
                case 1:
                    active = homeFragment;
                    break;
                case 2:
                    active = new twofragments();
                    break;
                case 3:
                    active = searchFragment;
                    break;
                case 4:
                    active = new CartFragment();
                    break;
                case 5:
                    active = new AccountFragment();
                    break;
            }
            HelperMethod.replace(active, fm, R.id.fragment_container, false);
        });

        meo.setOnShowListener(item -> {
        });
        meo.setOnReselectListener(item -> {
        });
        meo.show(1, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new UserPrefs(this).loadLocate(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
//        getSupportActionBar().setElevation(0);
//
//        View view = getSupportActionBar().getCustomView();
        getSupportActionBar().hide();

        search = findViewById(R.id.action_bar_search);
        searchEt = findViewById(R.id.search_et);
        clearIv = findViewById(R.id.clear_iv);
        cartIv = findViewById(R.id.action_bar_cart);

        searchEt.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                focus = true;
                clearIv.setVisibility(View.VISIBLE);
                search.setImageResource(R.drawable.ic_left_arrow__2_);
                meo.show(3, true);
                if (!searchFragment.isAdded()) {
                    HelperMethod.replace(searchFragment, fm, R.id.fragment_container, false);
                }
            } else {
                focus = false;
            }
        });
        clearIv.setOnClickListener(v -> searchEt.getText().clear());
        search.setOnClickListener(v -> {
            if (focus) {
                if (!searchEt.getText().toString().isEmpty()) {
                    searchEt.getText().clear();
                }
                searchEt.clearFocus();
                search.setImageResource(R.drawable.ic_search);
                clearIv.setVisibility(View.GONE);
                meo.show(tabId, true);
                hideSoftKeyboard(searchEt);
                HelperMethod.replace(active, fm, R.id.fragment_container, false);
            } else {

            }
        });

        cartIv.setOnClickListener(v -> {
            meo.show(4, true);
            HelperMethod.replace(cartFragment, fm, R.id.fragment_container, false);
            active = cartFragment;
        });

        ImageView drawerBtn = findViewById(R.id.action_menu);
        drawerBtn.setClickable(true);


        drawer = findViewById(R.id.drawer_layout);
        drawerBtn.setOnClickListener(v -> drawer.openDrawer(GravityCompat.START));


        HelperMethod.replace(homeFragment, fm, R.id.fragment_container, false);
        initBottom();
        setDrawer();

        email.setOnClickListener(v -> {
            if (email.getText().equals(getString(R.string.sign_or_register))) {
                startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), 100);
                drawer.closeDrawer(GravityCompat.START);
            }
        });
    }

    void setDrawer() {
        name = findViewById(R.id.name_drawer_tv);
        email = findViewById(R.id.email_drawer_tv);

        toolbar = findViewById(R.id.toolbarr);

        UserPrefs userPrefs = new UserPrefs(MainActivity.this);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if (userPrefs.getAuthPreferenceObjectJson("auth_response") != null) {
                    name.setVisibility(View.VISIBLE);
                    name.setText(userPrefs.getAuthPreferenceObjectJson("auth_response").getUser().getName());
                    email.setText(userPrefs.getAuthPreferenceObjectJson("auth_response").getUser().getEmail());
                } else {
                    name.setVisibility(View.GONE);
                    email.setText(getText(R.string.sign_or_register));
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        drawerRv = findViewById(R.id.category_rv_drawer);
        drawerRv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        DrawerCategoryAdapter drawerCategoryAdapter = new DrawerCategoryAdapter(MainActivity.this, mCategories, this);
        drawerRv.setAdapter(drawerCategoryAdapter);


        apiService = ApiClient.getClient().create(getProductsWithSubcategory.class);
        Call<collectionmodel> getProducts = apiService.get_Products_With_SubCategory("Mobile accessories");

        getProducts.enqueue(new Callback<collectionmodel>() {
            @Override
            public void onResponse(Call<collectionmodel> call, Response<collectionmodel> response) {
                try {
                    collectionmodel model = response.body();
                    mCategories.addAll(model.getData().get(0).getSubCategories());
                    drawerCategoryAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<collectionmodel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    protected void hideSoftKeyboard(EditText input) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent().getExtras() != null) {
            String message = getIntent().getStringExtra("message");
            String position = getIntent().getStringExtra("position");

            CustomToast.showToast(this, message, R.color.colorSuccess);
            getIntent().removeExtra("message");
            getIntent().removeExtra("position");

            if (position.equals("cart")) {
//                loadFragment(cartFragment);
//                navView.setSelectedItemId(R.id.navigation_cart);
            } else if (position.equals("account")) {
//                loadFragment(accountFragment);
//                navView.setSelectedItemId(R.id.navigation_account);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (active == homeFragment) {
            super.onBackPressed();
        } else {
            HelperMethod.replace(homeFragment, fm, R.id.fragment_container, false);
            meo.show(1, true);
            active = homeFragment;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            new AppSettingsInteractorImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).execute();
        }
        if (resultCode == Activity.RESULT_CANCELED) {

        }

    }

    @Override
    public void onAppSettingsLoaded(AppSettingsResponse appSettingsResponse) {
        UserPrefs userPrefs = new UserPrefs(this);
        userPrefs.setAppSettingsPreferenceObject(appSettingsResponse, "app_settings_response");
        accountFragment = new AccountFragment();
        fm.beginTransaction().remove(accountFragment).commitAllowingStateLoss();
        fm.beginTransaction().add(R.id.fragment_container, accountFragment, "account").hide(accountFragment).commitAllowingStateLoss();
        fm.beginTransaction().hide(active).show(accountFragment).commitAllowingStateLoss();
        active = accountFragment;
    }

    @Override
    public void onAppSettingsLoadedError() {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCategoryItemClick(SubCategorymodel model, int pos) {
        int categoryId = 1;
        int subCategoryID = model.getId();
        Log.i("URLLLL", "https://www.gomlgy.com/api/v1/get-product?category_id=" + categoryId + "&sub_category_id=" + subCategoryID);
        Intent i = new Intent(getApplicationContext(), ProductListingActivity.class);
        i.putExtra("url", "https://www.gomlgy.com/api/v1/get-product?category_id=" + categoryId + "&sub_category_id=" + subCategoryID);
        i.putExtra("title", model.getName());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        drawer.closeDrawer(GravityCompat.START);
        MainActivity.this.startActivity(i);
    }
}
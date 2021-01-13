package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.onoo.gomlgy.Network.response.AuthResponse;
import com.onoo.gomlgy.Network.response.ProductListingResponse;
import com.onoo.gomlgy.Network.response.WarrantyResponse;
import com.onoo.gomlgy.Presentation.presenters.WarrantyPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.WarrantyView;
import com.onoo.gomlgy.Presentation.ui.adapters.WarrantyAdapter;
import com.onoo.gomlgy.Presentation.ui.fragments.impl.AddWarrantyFragment;
import com.onoo.gomlgy.Presentation.ui.listeners.OnEndLess;
import com.onoo.gomlgy.Presentation.ui.listeners.WarrantyListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.HelperMethod;
import com.onoo.gomlgy.Utils.UserPrefs;
import com.onoo.gomlgy.databinding.ActivityWarrantyBinding;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.onoo.gomlgy.models.WarrantyData;

import java.util.ArrayList;
import java.util.List;

public class WarrantyActivity extends BaseActivity implements WarrantyView, WarrantyListener {

    ActivityWarrantyBinding binding;
    WarrantyPresenter warrantyPresenter;
    private AuthResponse authResponse;

    private static final String TAG = "WarrantyActivityy";

    FragmentManager fm = getSupportFragmentManager();
    AddWarrantyFragment addWarrantyFragment;
    private int userId;
    WarrantyAdapter adapter;

    private OnEndLess onEndLess;
    private int MaxPage;
    LinearLayoutManager layoutManager;
    List<WarrantyData> warrantyDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(WarrantyActivity.this, R.layout.activity_warranty);

        initializeActionBar();
        setTitle(getString(R.string.warranty));

        warrantyPresenter = new WarrantyPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        authResponse = new UserPrefs(getApplicationContext()).getAuthPreferenceObjectJson("auth_response");
        if (authResponse != null && authResponse.getUser() != null) {
            userId = authResponse.getUser().getId();
            warrantyPresenter.getWarrantyItems(1, userId);
            Log.i(TAG, " e " + authResponse.getUser().getId());
        } else {
        }

        binding.noRequestsTv.setVisibility(View.VISIBLE);

        binding.addWarranty.setOnClickListener(v -> {
            addWarrantyFragment = new AddWarrantyFragment();
            addWarrantyFragment.TYPE = 1;
            HelperMethod.add(addWarrantyFragment, fm, R.id.warranty_fl_content, true);
        });


        layoutManager = new LinearLayoutManager(this);
        adapter = new WarrantyAdapter();
        binding.warrantyRv.setLayoutManager(layoutManager);
        binding.warrantyRv.setAdapter(adapter);
        adapter.setOnMenuClickListener(this);

        onEndLess = new OnEndLess(layoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= MaxPage) {
                    if (MaxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        warrantyPresenter.getWarrantyItems(current_page, userId);
                        adapter.notifyDataSetChanged();
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        binding.warrantyRv.addOnScrollListener(onEndLess);

    }

    @Override
    public void setWarrantyList(WarrantyResponse warrantyResponse) {
//        if (warrantyResponse.getSuccess()) {
            warrantyDataList.addAll(warrantyResponse.getData().getData());
            Log.i(TAG, " current : " + warrantyResponse.getData().getCurrentPage());
            MaxPage = warrantyResponse.getData().getLastPage();
            adapter.setItems(warrantyDataList);
            binding.noRequestsTv.setVisibility(View.GONE);
//        } else {
//
//        }
    }

    @Override
    public void onWarrantyDeleted(int warrantyId) {
        adapter.deleteItem(warrantyId);
    }

    @Override
    public void onItemMenuClickListener(WarrantyData warrantyData, int type) {
        if (type == 1) {
            addWarrantyFragment = new AddWarrantyFragment();
            addWarrantyFragment.warrantyData = warrantyData;
            addWarrantyFragment.TYPE = 2;
            HelperMethod.add(addWarrantyFragment, fm, R.id.warranty_fl_content, true);
        } else if (type == 2) {
            warrantyPresenter.deleteWarrantyItem(warrantyData.getId());
        }
    }
}
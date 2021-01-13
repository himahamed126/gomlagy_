package com.onoo.gomlgy.Presentation.ui.fragments.impl;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.onoo.gomlgy.Network.response.AddWarrantyRequestResponse;
import com.onoo.gomlgy.Network.response.AuthResponse;
import com.onoo.gomlgy.Network.response.MyPurchasesResponse;
import com.onoo.gomlgy.Presentation.presenters.AddWarrantyPresenter;
import com.onoo.gomlgy.Presentation.ui.adapters.MyPurchasesAdapter;
import com.onoo.gomlgy.Presentation.ui.fragments.AddWarrantyView;
import com.onoo.gomlgy.Presentation.ui.listeners.MyPurchasesListener;
import com.onoo.gomlgy.Presentation.ui.listeners.OnEndLess;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.UserPrefs;
import com.onoo.gomlgy.databinding.FragmentAddWarrantyBinding;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.onoo.gomlgy.models.MyPurchasesData;
import com.onoo.gomlgy.models.WarrantyData;

import java.util.ArrayList;
import java.util.List;


public class AddWarrantyFragment extends Fragment implements AddWarrantyView, MyPurchasesListener {
    private FragmentAddWarrantyBinding binding;
    private AddWarrantyPresenter presenter;
    private AuthResponse authResponse;
    private static final String TAG = "AddWar";

    private int userId;
    private int orderId;
    // if type = 1 add //  type =2 edit
    public int TYPE;
    public WarrantyData warrantyData;


    private OnEndLess onEndLess;
    private int MaxPage;
    List<MyPurchasesData> myPurchasesList = new ArrayList<>();
    MyPurchasesAdapter adapter;
    LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_warranty, container, false);


        binding.purchaseProgresBar.setVisibility(View.VISIBLE);

        presenter = new AddWarrantyPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        authResponse = new UserPrefs(getActivity()).getAuthPreferenceObjectJson("auth_response");
        if (authResponse != null && authResponse.getUser() != null) {
            userId = authResponse.getUser().getId();
            presenter.getMyPurchasesItems(1, userId);
        } else {
        }


        binding.sendBtn.setOnClickListener(v -> {
            if (TYPE == 1) {
                sendWarrantyRequest();
            } else if (TYPE == 2) {
                editWarrantyRequest();
            }
        });

        if (TYPE == 1) {

        } else if (TYPE == 2) {
            binding.reasonEd.setText(warrantyData.getReason());
            binding.sendBtn.setText(getString(R.string.ediit));

        }

        adapter = new MyPurchasesAdapter(getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        binding.purchaseRv.setLayoutManager(layoutManager);
        binding.purchaseRv.setAdapter(adapter);
        adapter.setOnPurchasesClickListener(this);


        onEndLess = new OnEndLess(layoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= MaxPage) {
                    if (MaxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        presenter.getMyPurchasesItems(current_page, userId);
                        adapter.notifyDataSetChanged();
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        binding.purchaseRv.addOnScrollListener(onEndLess);


        return binding.getRoot();
    }

    @Override
    public void setMyPurchases(MyPurchasesResponse purchasesResponse) {

        Log.i(TAG, "size : " + purchasesResponse.getData().getData().size());
        binding.purchaseProgresBar.setVisibility(View.INVISIBLE);
        myPurchasesList.addAll(purchasesResponse.getData().getData());
        MaxPage = purchasesResponse.getData().getLastPage();
        adapter.setItems(myPurchasesList);
        if (TYPE == 2) {
            adapter.setSelectedItem(warrantyData.getOrderId());
        }

    }


    @Override
    public void onItemPurchasesClickListener(MyPurchasesData myPurchasesData, int type) {
        orderId = myPurchasesData.getId();
    }

    void sendWarrantyRequest() {
        String reason = binding.reasonEd.getText().toString();

        if (reason.length() == 0) {
            Snackbar.make(binding.getRoot(), R.string.enter_the_reason, Snackbar.LENGTH_SHORT).show();
        } else {
            presenter.addWarrantyRequest(orderId, reason);
        }
    }

    void editWarrantyRequest() {
        String reason = binding.reasonEd.getText().toString();

        if (reason.length() < 4) {
            Snackbar.make(binding.getRoot(), R.string.enter_the_reason, Snackbar.LENGTH_SHORT).show();
        } else {
            presenter.updateWarrantyRequest(warrantyData.getId(), warrantyData.getOrderId(), reason);
        }
    }

    @Override
    public void onAddWarrantyRequestSubmitted(AddWarrantyRequestResponse addWarrantyRequestResponse) {
        if (addWarrantyRequestResponse.getSuccess()) {
            Snackbar.make(binding.getRoot(), addWarrantyRequestResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().popBackStack();
            getActivity().recreate();

        } else {
            Snackbar.make(binding.getRoot(), addWarrantyRequestResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpdateWarrantyRequestSubmitted(AddWarrantyRequestResponse addWarrantyRequestResponse) {
        if (addWarrantyRequestResponse.getSuccess()) {
            Snackbar.make(binding.getRoot(), addWarrantyRequestResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().popBackStack();
            getActivity().recreate();
        } else {
            Snackbar.make(binding.getRoot(), addWarrantyRequestResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
        }
    }
}

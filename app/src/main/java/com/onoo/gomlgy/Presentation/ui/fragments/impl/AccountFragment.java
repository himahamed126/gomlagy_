package com.onoo.gomlgy.Presentation.ui.fragments.impl;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.onoo.gomlgy.Network.response.AppSettingsResponse;
import com.onoo.gomlgy.Network.response.AuthResponse;
import com.onoo.gomlgy.Network.response.LogoutResponse;
import com.onoo.gomlgy.Presentation.ui.activities.impl.AccountInfoActivity;
import com.onoo.gomlgy.Presentation.ui.activities.impl.LoginActivity;
import com.onoo.gomlgy.Presentation.ui.activities.impl.PurchaseHistoryActivity;
import com.onoo.gomlgy.Presentation.ui.activities.impl.WalletActivity;
import com.onoo.gomlgy.Presentation.ui.activities.impl.WishlistActivity;
import com.onoo.gomlgy.Presentation.ui.fragments.AccountView;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.CustomToast;
import com.onoo.gomlgy.Utils.UserPrefs;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.onoo.gomlgy.domain.interactors.AppSettingsInteractor;
import com.onoo.gomlgy.domain.interactors.impl.AppSettingsInteractorImpl;

import q.rorbin.badgeview.QBadgeView;

//import static com.onoo.gomlgy.Presentation.ui.activities.impl.MainActivity.navView;

public class AccountFragment extends Fragment implements AccountView, AppSettingsInteractor.CallBack {
    private View v;
    private AuthResponse authResponse;
    private RelativeLayout wishlist, purchase_history, wallet_info, account_info, language, logout;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_account, null);

        initViews();

        authResponse = new UserPrefs(getActivity()).getAuthPreferenceObjectJson("auth_response");

        return v;
    }

    private void initViews() {

        language = v.findViewById(R.id.language);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserPrefs(getActivity()).showChangeLanguage(getActivity());
            }
        });


        wishlist = v.findViewById(R.id.wishlist);
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (authResponse != null && authResponse.getUser() != null) {
                    startActivity(new Intent(getContext(), WishlistActivity.class));
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 100);
                }
            }
        });

        purchase_history = v.findViewById(R.id.purchase_history);
        purchase_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (authResponse != null && authResponse.getUser() != null) {
                    startActivity(new Intent(getContext(), PurchaseHistoryActivity.class));
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 100);
                }
            }
        });

        wallet_info = v.findViewById(R.id.wallet_info);
        wallet_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (authResponse != null && authResponse.getUser() != null) {
                    startActivity(new Intent(getContext(), WalletActivity.class));
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 100);
                }
            }
        });

        account_info = v.findViewById(R.id.account_info);
        account_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (authResponse != null && authResponse.getUser() != null) {
                    startActivity(new Intent(getContext(), AccountInfoActivity.class));
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 100);
                }
            }
        });

        logout = v.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (authResponse != null && authResponse.getUser() != null) {
                    new UserPrefs(getContext()).clearPreference();

                    new AppSettingsInteractorImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), AccountFragment.this).execute();

                    logout.setVisibility(View.GONE);

//                    getActivity().recreate();

//                    BottomNavigationMenuView bottomNavigationMenuView =
//                            (BottomNavigationMenuView) navView.getChildAt(0);
//                    View view = bottomNavigationMenuView.getChildAt(3); // number of menu from left
//                    new QBadgeView(getActivity()).bindTarget(view).setBadgeText(String.valueOf(0)).setShowShadow(false);
                }
            }
        });
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void showLogoutMessage(LogoutResponse logoutResponse) {
        CustomToast.showToast(getActivity(), logoutResponse.getMessage(), R.color.colorInfo);
        new UserPrefs(getContext()).clearPreference();

        new AppSettingsInteractorImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).execute();


        logout.setVisibility(View.GONE);


//        BottomNavigationMenuView bottomNavigationMenuView =
//                (BottomNavigationMenuView) navView.getChildAt(0);
//        View v = bottomNavigationMenuView.getChildAt(3); // number of menu from left
        new QBadgeView(getActivity()).bindTarget(v).setBadgeText(String.valueOf(0)).setShowShadow(false);
    }

    @Override
    public void onAppSettingsLoaded(AppSettingsResponse appSettingsResponse) {
        UserPrefs userPrefs = new UserPrefs(getContext());
        userPrefs.setAppSettingsPreferenceObject(appSettingsResponse, "app_settings_response");
    }

    @Override
    public void onAppSettingsLoadedError() {

    }
}

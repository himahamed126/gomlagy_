package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.Network.response.LogoutResponse;
import com.onoo.gomlgy.Presentation.ui.fragments.AccountView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.LogoutInteractor;
import com.onoo.gomlgy.domain.interactors.impl.LogoutInteractorImpl;

public class AccountPresenter extends AbstractPresenter implements LogoutInteractor.CallBack {

    private AccountView accountView;

    public AccountPresenter(Executor executor, MainThread mainThread, AccountView accountView) {
        super(executor, mainThread);
        this.accountView = accountView;
    }

    public void performLogout(String token){
        new LogoutInteractorImpl(mExecutor, mMainThread, this, token).execute();
    }

    @Override
    public void onLoggedOut(LogoutResponse logoutResponse) {
        if(accountView != null){
            accountView.showLogoutMessage(logoutResponse);
        }
    }

    @Override
    public void onLoggedOutError() {

    }
}

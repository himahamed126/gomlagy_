package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.Models.PurchaseHistory;
import com.onoo.gomlgy.Presentation.ui.activities.PurchaseHistoryView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.PurchaseHistoryInteractor;
import com.onoo.gomlgy.domain.interactors.impl.PurchaseHistoryInteractorImpl;

import java.util.List;

public class PurchaseHistoryPresenter extends AbstractPresenter implements PurchaseHistoryInteractor.CallBack {
    private PurchaseHistoryView purchaseHistoryView;

    public PurchaseHistoryPresenter(Executor executor, MainThread mainThread, PurchaseHistoryView purchaseHistoryView) {
        super(executor, mainThread);
        this.purchaseHistoryView = purchaseHistoryView;
    }

    public void getPurchaseHistoryItems(int id, String token) {
        new PurchaseHistoryInteractorImpl(mExecutor, mMainThread, this, id, token).execute();
    }

    @Override
    public void onPurchaseHistoryLodaded(List<PurchaseHistory> purchaseHistories) {
        if (purchaseHistoryView != null){
            purchaseHistoryView.onPurchaseHistoryLoaded(purchaseHistories);
        }
    }

    @Override
    public void onPurchaseHistoryLodadedError() {

    }
}

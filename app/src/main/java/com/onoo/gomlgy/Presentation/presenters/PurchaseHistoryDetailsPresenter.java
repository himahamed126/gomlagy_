package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.models.OrderDetail;
import com.onoo.gomlgy.Presentation.ui.activities.PurchaseHistoryDetailsView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.PurchaseHistoryDetailsInteractor;
import com.onoo.gomlgy.domain.interactors.impl.PurchaseHistoryDetailsInteractorImpl;

import java.util.List;

public class PurchaseHistoryDetailsPresenter extends AbstractPresenter implements PurchaseHistoryDetailsInteractor.CallBack {

    private PurchaseHistoryDetailsView purchaseHistoryDetailsView;

    public PurchaseHistoryDetailsPresenter(Executor executor, MainThread mainThread, PurchaseHistoryDetailsView purchaseHistoryDetailsView) {
        super(executor, mainThread);
        this.purchaseHistoryDetailsView = purchaseHistoryDetailsView;
    }

    public void getOrderDetails(String token, String url){
        new PurchaseHistoryDetailsInteractorImpl(mExecutor, mMainThread, this, url, token).execute();
    }

    @Override
    public void onOrderDetailsLoaded(List<OrderDetail> orderDetails) {
        if (purchaseHistoryDetailsView != null){
            purchaseHistoryDetailsView.onOrderDetailsLoaded(orderDetails);
        }
    }

    @Override
    public void onOrderDetailsLoadError() {

    }
}

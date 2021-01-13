package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.Network.response.AddWarrantyRequestResponse;
import com.onoo.gomlgy.Network.response.MyPurchasesResponse;
import com.onoo.gomlgy.Presentation.ui.fragments.AddWarrantyView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.AddWarrantyRequestInteractor;
import com.onoo.gomlgy.domain.interactors.MyPurchasesInteractor;
import com.onoo.gomlgy.domain.interactors.UpdateWarrantyRequestInteractor;
import com.onoo.gomlgy.domain.interactors.impl.AddWarrantyInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.MyPurchasesInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.UpdateWarrantyInteractorImpl;
import com.onoo.gomlgy.models.MyPurchasesData;

import java.util.List;

public class AddWarrantyPresenter extends AbstractPresenter implements MyPurchasesInteractor.CallBack, AddWarrantyRequestInteractor.CallBack, UpdateWarrantyRequestInteractor.CallBack {
    AddWarrantyView addWarrantyView;

    public AddWarrantyPresenter(Executor executor, MainThread mainThread, AddWarrantyView addWarrantyView) {
        super(executor, mainThread);
        this.addWarrantyView = addWarrantyView;
    }

    public void getMyPurchasesItems(int page,int id) {
        new MyPurchasesInteractorImpl(mExecutor, mMainThread, this, page, id).execute();
    }

    public void addWarrantyRequest(int orderId, String reason) {
        new AddWarrantyInteractorImpl(mExecutor, mMainThread, this, orderId, reason).execute();

    }

    public void updateWarrantyRequest(int warranty_id, int orderId, String reason) {
        new UpdateWarrantyInteractorImpl(mExecutor, mMainThread, this, warranty_id, orderId, reason).execute();

    }

    @Override
    public void onPurchasesLoaded(MyPurchasesResponse myPurchasesResponse) {
        if (addWarrantyView != null) {
            addWarrantyView.setMyPurchases(myPurchasesResponse);
        }
    }

    @Override
    public void onPurchasesError() {

    }

    @Override
    public void onAddWarrantySubmitted(AddWarrantyRequestResponse addWarrantyRequestResponse) {
        if (addWarrantyView != null) {
            addWarrantyView.onAddWarrantyRequestSubmitted(addWarrantyRequestResponse);
        }
    }

    @Override
    public void onAddWarrantyError() {

    }

    @Override
    public void onUpdateWarrantySubmitted(AddWarrantyRequestResponse addWarrantyRequestResponse) {
        if (addWarrantyView != null) {
            addWarrantyView.onUpdateWarrantyRequestSubmitted(addWarrantyRequestResponse);
        }
    }

    @Override
    public void onUpdateWarrantyError() {

    }
}

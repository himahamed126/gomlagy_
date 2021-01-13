package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.Network.response.WarrantyResponse;
import com.onoo.gomlgy.Presentation.ui.activities.WarrantyView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.DeleteItemWarrantyInteractor;
import com.onoo.gomlgy.domain.interactors.WarrantyInteractor;
import com.onoo.gomlgy.domain.interactors.impl.DeleteWarrantyItemInteractorImpl;
import com.onoo.gomlgy.domain.interactors.impl.WarrantyInteractorImpl;
import com.onoo.gomlgy.models.WarrantyData;

import java.util.List;

public class WarrantyPresenter extends AbstractPresenter implements WarrantyInteractor.CallBack, DeleteItemWarrantyInteractor.CallBack {

    WarrantyView warrantyView;

    public WarrantyPresenter(Executor executor, MainThread mainThread, WarrantyView warrantyView) {
        super(executor, mainThread);
        this.warrantyView = warrantyView;
    }

    public void getWarrantyItems(int page, int userID) {
        new WarrantyInteractorImpl(mExecutor, mMainThread, this, page, userID).execute();
    }

    public void deleteWarrantyItem(int userID) {
        new DeleteWarrantyItemInteractorImpl(mExecutor, mMainThread, this, userID).execute();
    }

    @Override
    public void onWarrantyLoaded(WarrantyResponse warrantyResponse) {
        if (warrantyView != null) {
            warrantyView.setWarrantyList(warrantyResponse);
        }
    }

    @Override
    public void onWarrantyDeleted(int warrantyId) {
        if (warrantyView != null) {
            warrantyView.onWarrantyDeleted(warrantyId);
        }
    }

    @Override
    public void onWarrantyError() {

    }
}

package com.onoo.gomlgy.domain.interactors;

public interface DeleteItemWarrantyInteractor {
    interface CallBack {
        void onWarrantyDeleted(int warrantyId);
        void onWarrantyError();
    }
}

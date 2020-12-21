package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.PurchaseHistory;

import java.util.List;

public interface PurchaseHistoryInteractor {
    interface CallBack {

        void onPurchaseHistoryLodaded(List<PurchaseHistory> purchaseHistories);

        void onPurchaseHistoryLodadedError();
    }
}

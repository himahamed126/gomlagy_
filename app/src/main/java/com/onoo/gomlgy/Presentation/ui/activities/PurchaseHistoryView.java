package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.models.PurchaseHistory;

import java.util.List;

public interface PurchaseHistoryView {
    void onPurchaseHistoryLoaded(List<PurchaseHistory> purchaseHistoryList);
}

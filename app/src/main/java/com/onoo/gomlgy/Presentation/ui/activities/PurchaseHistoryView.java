package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.Models.PurchaseHistory;

import java.util.List;

public interface PurchaseHistoryView {
    void onPurchaseHistoryLoaded(List<PurchaseHistory> purchaseHistoryList);
}

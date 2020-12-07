package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.Models.OrderDetail;

import java.util.List;

public interface PurchaseHistoryDetailsView {
    void onOrderDetailsLoaded(List<OrderDetail> orderDetailList);
}

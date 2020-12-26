package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.OrderDetail;

import java.util.List;

public interface PurchaseHistoryDetailsInteractor {
    interface CallBack {

        void onOrderDetailsLoaded(List<OrderDetail> orderDetails);

        void onOrderDetailsLoadError();
    }
}

package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.MyPurchasesResponse;
import com.onoo.gomlgy.models.MyPurchasesData;

import java.util.List;

public interface MyPurchasesInteractor {
    interface CallBack {
        void onPurchasesLoaded(MyPurchasesResponse myPurchasesResponse);

        void onPurchasesError();
    }
}

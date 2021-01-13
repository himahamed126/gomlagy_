package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.WarrantyResponse;
import com.onoo.gomlgy.models.WarrantyData;

import java.util.List;

public interface WarrantyInteractor {
    interface CallBack {
        void onWarrantyLoaded(WarrantyResponse warrantyResponse);
        void onWarrantyError();
    }
}

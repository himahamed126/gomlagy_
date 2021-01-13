package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.AddWarrantyRequestResponse;

public interface UpdateWarrantyRequestInteractor {
    interface CallBack {
        void onUpdateWarrantySubmitted(AddWarrantyRequestResponse addWarrantyRequestResponse);

        void onUpdateWarrantyError();
    }
}

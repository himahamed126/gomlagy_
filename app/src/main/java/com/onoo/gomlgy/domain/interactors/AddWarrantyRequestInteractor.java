package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Network.response.AddWarrantyRequestResponse;

public interface AddWarrantyRequestInteractor {
    interface CallBack {
        void onAddWarrantySubmitted(AddWarrantyRequestResponse addWarrantyRequestResponse);

        void onAddWarrantyError();
    }
}

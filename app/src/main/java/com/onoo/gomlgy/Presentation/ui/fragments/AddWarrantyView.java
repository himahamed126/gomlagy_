package com.onoo.gomlgy.Presentation.ui.fragments;

import com.onoo.gomlgy.Network.response.AddWarrantyRequestResponse;
import com.onoo.gomlgy.Network.response.MyPurchasesResponse;
import com.onoo.gomlgy.models.MyPurchasesData;

import java.util.List;

public interface AddWarrantyView {
    void setMyPurchases(MyPurchasesResponse myPurchasesResponse);

    void onAddWarrantyRequestSubmitted(AddWarrantyRequestResponse addWarrantyRequestResponse);

    void onUpdateWarrantyRequestSubmitted(AddWarrantyRequestResponse addWarrantyRequestResponse);
}

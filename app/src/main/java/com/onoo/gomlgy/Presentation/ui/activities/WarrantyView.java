package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.Network.response.WarrantyResponse;
import com.onoo.gomlgy.models.WarrantyData;

import java.util.List;

public interface WarrantyView {

    void setWarrantyList(WarrantyResponse warrantyResponse);

    void onWarrantyDeleted(int warrantyId);
}

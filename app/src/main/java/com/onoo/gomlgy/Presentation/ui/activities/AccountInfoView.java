package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.models.ShippingAddress;
import com.onoo.gomlgy.models.User;
import com.onoo.gomlgy.Network.response.ProfileInfoUpdateResponse;
import com.onoo.gomlgy.Network.response.ShippingInfoResponse;

import java.util.List;

public interface AccountInfoView {
    void onProfileInfoUpdated(ProfileInfoUpdateResponse profileInfoUpdateResponse);
    void setShippingAddresses(List<ShippingAddress> shippingAddresses);
    void onShippingInfoCreated(ShippingInfoResponse shippingInfoResponse);
    void onShippingInfoDeleted(ShippingInfoResponse shippingInfoResponse);
    void setUpdatedUserInfo(User user);
}

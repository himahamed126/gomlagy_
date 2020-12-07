package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.Models.UserBid;
import com.onoo.gomlgy.Network.response.AuctionBidResponse;

import java.util.List;

public interface MybidView {
    void setUserBids(List<UserBid> userBids);
    void onAuctionBidSubmitted(AuctionBidResponse auctionBidResponse);
}

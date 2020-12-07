package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.Models.Wallet;

import java.util.List;

public interface WalletView {
    void setWalletBalance(Double balance);
    void setWalletHistory(List<Wallet> walletList);
}

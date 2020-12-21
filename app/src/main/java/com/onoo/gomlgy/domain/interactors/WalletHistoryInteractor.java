package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.Wallet;

import java.util.List;

public interface WalletHistoryInteractor {
    interface CallBack {

        void onWalletHistoryLodaded(List<Wallet> walletList);

        void onWalletHistoryLoadError();
    }
}

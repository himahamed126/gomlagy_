package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Models.Shop;

public interface ShopInteractor {
    interface CallBack {

        void onShopLoaded(Shop shop);

        void onShopLoadError();
    }
}

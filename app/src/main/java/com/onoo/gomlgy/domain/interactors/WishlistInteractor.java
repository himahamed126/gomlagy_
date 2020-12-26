package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.models.WishlistModel;

import java.util.List;

public interface WishlistInteractor {
    interface CallBack {

        void onWishlistLodaded(List<WishlistModel> wishlistModels);

        void onWishlistError();
    }
}

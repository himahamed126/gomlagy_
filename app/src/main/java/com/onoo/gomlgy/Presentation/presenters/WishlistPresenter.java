package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.models.WishlistModel;
import com.onoo.gomlgy.Presentation.ui.activities.WishlistView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.WishlistInteractor;
import com.onoo.gomlgy.domain.interactors.impl.WishlistInteractorImpl;

import java.util.List;

public class WishlistPresenter extends AbstractPresenter implements WishlistInteractor.CallBack {
    private WishlistView wishlistView;

    public WishlistPresenter(Executor executor, MainThread mainThread, WishlistView wishlistView) {
        super(executor, mainThread);
        this.wishlistView = wishlistView;
    }

    public void getWishlistItems(int id, String token) {
        new WishlistInteractorImpl(mExecutor, mMainThread, this, id, token).execute();
    }

    public void removeWishlistItem(int id, String token){
        new WishlistInteractorImpl(mExecutor, mMainThread, this, id, token).execute();
    }

    @Override
    public void onWishlistLodaded(List<WishlistModel> wishlistModels) {
        if(wishlistView != null){
            wishlistView.setWishlistProducts(wishlistModels);
        }
    }

    @Override
    public void onWishlistError() {

    }
}

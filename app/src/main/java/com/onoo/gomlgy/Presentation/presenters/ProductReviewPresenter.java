package com.onoo.gomlgy.Presentation.presenters;

import com.onoo.gomlgy.models.Review;
import com.onoo.gomlgy.Presentation.ui.activities.ProductReviewView;
import com.onoo.gomlgy.domain.executor.Executor;
import com.onoo.gomlgy.domain.executor.MainThread;
import com.onoo.gomlgy.domain.interactors.ReviewInteractor;
import com.onoo.gomlgy.domain.interactors.impl.ReviewInteractorImpl;

import java.util.List;

public class ProductReviewPresenter extends AbstractPresenter implements ReviewInteractor.CallBack {
    private ProductReviewView productReviewView;

    public ProductReviewPresenter(Executor executor, MainThread mainThread, ProductReviewView productReviewView) {
        super(executor, mainThread);
        this.productReviewView = productReviewView;
    }

    public void sendUpdateProfileRequest(String url) {
        new ReviewInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }


    @Override
    public void onReviewLodaded(List<Review> reviews) {
        if (productReviewView != null){
            productReviewView.onReviewsLoaded(reviews);
        }
    }

    @Override
    public void onReviewError() {

    }
}

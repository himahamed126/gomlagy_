package com.onoo.gomlgy.domain.interactors;

import com.onoo.gomlgy.Models.Review;

import java.util.List;

public interface ReviewInteractor {
    interface CallBack {

        void onReviewLodaded(List<Review> reviews);

        void onReviewError();
    }
}

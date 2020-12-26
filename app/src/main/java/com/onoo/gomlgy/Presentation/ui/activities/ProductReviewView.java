package com.onoo.gomlgy.Presentation.ui.activities;

import com.onoo.gomlgy.models.Review;

import java.util.List;

public interface ProductReviewView {
    void onReviewsLoaded(List<Review> reviews);
}

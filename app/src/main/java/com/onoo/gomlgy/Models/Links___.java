package com.onoo.gomlgy.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Links___ implements Serializable {

    @SerializedName("reviews")
    @Expose
    private String reviews;
    @SerializedName("related")
    @Expose
    private String related;

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

}

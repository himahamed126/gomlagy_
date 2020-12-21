
package com.onoo.gomlgy.models.offers_sources.offers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OffersData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("offer_image")
    @Expose
    private String offerImage;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("offer_image_path")
    @Expose
    private String offerImagePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOfferImage() {
        return offerImage;
    }

    public void setOfferImage(String offerImage) {
        this.offerImage = offerImage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getOfferImagePath() {
        return offerImagePath;
    }

    public void setOfferImagePath(String offerImagePath) {
        this.offerImagePath = offerImagePath;
    }

}

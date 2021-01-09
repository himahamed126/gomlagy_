
package com.onoo.gomlgy.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductSearch {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("thumbnail_image")
    @Expose
    private List<String> thumbnailImage = null;
    @SerializedName("unit_price")
    @Expose
    private Integer unitPrice;
    @SerializedName("unit_price2")
    @Expose
    private Integer unitPrice2;
    @SerializedName("unit_price3")
    @Expose
    private Integer unitPrice3;
    @SerializedName("base_price")
    @Expose
    private Integer basePrice;
    @SerializedName("base_discounted_price")
    @Expose
    private Integer baseDiscountedPrice;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("links")
//    @Expose
//    private Links links;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(List<String> thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getUnitPrice2() {
        return unitPrice2;
    }

    public void setUnitPrice2(Integer unitPrice2) {
        this.unitPrice2 = unitPrice2;
    }

    public Integer getUnitPrice3() {
        return unitPrice3;
    }

    public void setUnitPrice3(Integer unitPrice3) {
        this.unitPrice3 = unitPrice3;
    }

    public Integer getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Integer basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getBaseDiscountedPrice() {
        return baseDiscountedPrice;
    }

    public void setBaseDiscountedPrice(Integer baseDiscountedPrice) {
        this.baseDiscountedPrice = baseDiscountedPrice;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

//    public Links getLinks() {
//        return links;
//    }
//
//    public void setLinks(Links links) {
//        this.links = links;
//    }


}

package com.onoo.gomlgy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photos")
    @Expose
    private List<String> photos = null;
    @SerializedName("thumbnail_image")
    @Expose
    private String thumbnailImage;
    //    @SerializedName("featured_image")
//    @Expose
//    private String featuredImage;
//    @SerializedName("flash_deal_image")
//    @Expose
//    private String flashDealImage;
    @SerializedName("base_price")
    @Expose
    private Double basePrice;
    @SerializedName("base_discounted_price")
    @Expose
    private Double baseDiscountedPrice;
    @SerializedName("todays_deal")
    @Expose
    private Integer todaysDeal;
    @SerializedName("featured")
    @Expose
    private Integer featured;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("discount")
    @Expose
    private Double discount;
    @SerializedName("discount_type")
    @Expose
    private String discountType;
    @SerializedName("rating")
    @Expose
    private float rating;
    @SerializedName("sales")
    @Expose
    private Integer sales;
    @SerializedName("links")
    @Expose
    private ProductLinks links;
    @SerializedName("unit_price")
    @Expose
    private Double unitPrice;
    @SerializedName("unit_price2")
    @Expose
    private Double unitPrice2;
    @SerializedName("unit_price3")
    @Expose
    private Double unitPrice3;

    private List<Double> prices;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    //    public String getFeaturedImage() {
//        return featuredImage;
//    }
//
//    public void setFeaturedImage(String featuredImage) {
//        this.featuredImage = featuredImage;
//    }
//
//    public String getFlashDealImage() {
//        return flashDealImage;
//    }
//
//    public void setFlashDealImage(String flashDealImage) {
//        this.flashDealImage = flashDealImage;
//    }
    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Double getBaseDiscountedPrice() {
        return baseDiscountedPrice;
    }

    public void setBaseDiscountedPrice(Double baseDiscountedPrice) {
        this.baseDiscountedPrice = baseDiscountedPrice;
    }

    public Integer getTodaysDeal() {
        return todaysDeal;
    }

    public void setTodaysDeal(Integer todaysDeal) {
        this.todaysDeal = todaysDeal;
    }

    public Integer getFeatured() {
        return featured;
    }

    public void setFeatured(Integer featured) {
        this.featured = featured;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public ProductLinks getLinks() {
        return links;
    }

    public void setLinks(ProductLinks links) {
        this.links = links;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getUnitPrice2() {
        return unitPrice2;
    }

    public void setUnitPrice2(Double unitPrice2) {
        this.unitPrice2 = unitPrice2;
    }

    public Double getUnitPrice3() {
        return unitPrice3;
    }

    public void setUnitPrice3(Double unitPrice3) {
        this.unitPrice3 = unitPrice3;
    }

    public List<Double> getPrices() {
        return prices;
    }

    public void setPrices(List<Double> prices) {
        this.prices = prices;
    }
}

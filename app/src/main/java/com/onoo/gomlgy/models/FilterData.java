package com.onoo.gomlgy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FilterData implements Serializable {

    @SerializedName("brands")
    @Expose
    private List<BrandData> brands = null;
    @SerializedName("min_price")
    @Expose
    private Integer minPrice;
    @SerializedName("max_price")
    @Expose
    private Integer maxPrice;

    public List<BrandData> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandData> brands) {
        this.brands = brands;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

}

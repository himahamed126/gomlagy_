package com.onoo.gomlgy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuantityPrice implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("min_quantity1")
    @Expose
    private Integer minQuantity1;
    @SerializedName("max_quantity1")
    @Expose
    private Integer maxQuantity1;
    @SerializedName("price1")
    @Expose
    private Integer price1;
    @SerializedName("min_quantity2")
    @Expose
    private Integer minQuantity2;
    @SerializedName("max_quantity2")
    @Expose
    private Integer maxQuantity2;
    @SerializedName("price2")
    @Expose
    private Integer price2;
    @SerializedName("min_quantity3")
    @Expose
    private Integer minQuantity3;
    @SerializedName("max_quantity3")
    @Expose
    private Integer maxQuantity3;
    @SerializedName("price3")
    @Expose
    private Integer price3;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("min_quantities")
    @Expose
    private Object minQuantities;
    @SerializedName("prices")
    @Expose
    private Object prices;
    @SerializedName("max_quantities")
    @Expose
    private Object maxQuantities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getMinQuantity1() {
        return minQuantity1;
    }

    public void setMinQuantity1(Integer minQuantity1) {
        this.minQuantity1 = minQuantity1;
    }

    public Integer getMaxQuantity1() {
        return maxQuantity1;
    }

    public void setMaxQuantity1(Integer maxQuantity1) {
        this.maxQuantity1 = maxQuantity1;
    }

    public Integer getPrice1() {
        return price1;
    }

    public void setPrice1(Integer price1) {
        this.price1 = price1;
    }

    public Integer getMinQuantity2() {
        return minQuantity2;
    }

    public void setMinQuantity2(Integer minQuantity2) {
        this.minQuantity2 = minQuantity2;
    }

    public Integer getMaxQuantity2() {
        return maxQuantity2;
    }

    public void setMaxQuantity2(Integer maxQuantity2) {
        this.maxQuantity2 = maxQuantity2;
    }

    public Integer getPrice2() {
        return price2;
    }

    public void setPrice2(Integer price2) {
        this.price2 = price2;
    }

    public Integer getMinQuantity3() {
        return minQuantity3;
    }

    public void setMinQuantity3(Integer minQuantity3) {
        this.minQuantity3 = minQuantity3;
    }

    public Integer getMaxQuantity3() {
        return maxQuantity3;
    }

    public void setMaxQuantity3(Integer maxQuantity3) {
        this.maxQuantity3 = maxQuantity3;
    }

    public Integer getPrice3() {
        return price3;
    }

    public void setPrice3(Integer price3) {
        this.price3 = price3;
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

    public Object getMinQuantities() {
        return minQuantities;
    }

    public void setMinQuantities(Object minQuantities) {
        this.minQuantities = minQuantities;
    }

    public Object getPrices() {
        return prices;
    }

    public void setPrices(Object prices) {
        this.prices = prices;
    }

    public Object getMaxQuantities() {
        return maxQuantities;
    }

    public void setMaxQuantities(Object maxQuantities) {
        this.maxQuantities = maxQuantities;
    }

}

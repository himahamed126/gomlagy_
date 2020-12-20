package com.onoo.gomlgy.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PurchaseHistory implements Serializable {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("shipping_address")
    @Expose
    private ShippingAddress shippingAddress;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("grand_total")
    @Expose
    private Double grandTotal;
    @SerializedName("shipping_cost")
    @Expose
    private Double shippingCost;
    @SerializedName("coupon_discount")
    @Expose
    private Double coupon_discount;
    @SerializedName("subtotal")
    @Expose
    private Double subtotal;
    @SerializedName("tax")
    @Expose
    private Double tax;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("links")
    @Expose
    private PurchaseHistoryLink links;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Double getCouponDiscount() {
        return coupon_discount;
    }

    public void setCouponDiscount(Double coupon_discount) {
        this.coupon_discount = coupon_discount;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PurchaseHistoryLink getLinks() {
        return links;
    }

    public void setLinks(PurchaseHistoryLink links) {
        this.links = links;
    }

    public PurchaseHistory(String code, User user, ShippingAddress shippingAddress, String paymentType, String paymentStatus, Double grandTotal, Double shippingCost, Double coupon_discount, Double subtotal, Double tax, String date, PurchaseHistoryLink links) {
        this.code = code;
        this.user = user;
        this.shippingAddress = shippingAddress;
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
        this.grandTotal = grandTotal;
        this.shippingCost = shippingCost;
        this.coupon_discount = coupon_discount;
        this.subtotal = subtotal;
        this.tax = tax;
        this.date = date;
        this.links = links;
    }
}

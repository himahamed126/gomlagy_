
package com.onoo.gomlgy.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyPurchasesData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("guest_id")
    @Expose
    private Object guestId;
    @SerializedName("shipping_address")
    @Expose
    private String shippingAddress;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("payment_details")
    @Expose
    private Object paymentDetails;
    @SerializedName("grand_total")
    @Expose
    private Integer grandTotal;
    @SerializedName("coupon_discount")
    @Expose
    private Integer couponDiscount;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("viewed")
    @Expose
    private Integer viewed;
    @SerializedName("delivery_viewed")
    @Expose
    private Integer deliveryViewed;
    @SerializedName("payment_status_viewed")
    @Expose
    private Integer paymentStatusViewed;
    @SerializedName("commission_calculated")
    @Expose
    private Integer commissionCalculated;
    @SerializedName("check_warranty")
    @Expose
    private String checkWarranty;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("order_details")
    @Expose
    private List<OrderDetail2> orderDetails = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Object getGuestId() {
        return guestId;
    }

    public void setGuestId(Object guestId) {
        this.guestId = guestId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
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

    public Object getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Object paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public Integer getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Integer grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Integer getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Integer couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    public Integer getDeliveryViewed() {
        return deliveryViewed;
    }

    public void setDeliveryViewed(Integer deliveryViewed) {
        this.deliveryViewed = deliveryViewed;
    }

    public Integer getPaymentStatusViewed() {
        return paymentStatusViewed;
    }

    public void setPaymentStatusViewed(Integer paymentStatusViewed) {
        this.paymentStatusViewed = paymentStatusViewed;
    }

    public Integer getCommissionCalculated() {
        return commissionCalculated;
    }

    public void setCommissionCalculated(Integer commissionCalculated) {
        this.commissionCalculated = commissionCalculated;
    }

    public String getCheckWarranty() {
        return checkWarranty;
    }

    public void setCheckWarranty(String checkWarranty) {
        this.checkWarranty = checkWarranty;
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

    public List<OrderDetail2> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail2> orderDetails) {
        this.orderDetails = orderDetails;
    }

}

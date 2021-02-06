
package com.onoo.gomlgy.models;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Utils.AppConfig;

public class WarrantyData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("order_code")
    @Expose
    private String orderCode;
    @SerializedName("order_detail_id")
    @Expose
    private Integer orderDetailId;
    @SerializedName("seller_id")
    @Expose
    private Integer sellerId;
    @SerializedName("duration_warranty")
    @Expose
    private Integer durationWarranty;
    @SerializedName("seller_approval")
    @Expose
    private Integer sellerApproval;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("admin_approval")
    @Expose
    private Integer adminApproval;
    @SerializedName("refund_amount")
    @Expose
    private Integer refundAmount;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("admin_seen")
    @Expose
    private Integer adminSeen;
    @SerializedName("refund_status")
    @Expose
    private Integer refundStatus;
    @SerializedName("check_warranty")
    @Expose
    private String checkWarranty;
    @SerializedName("start_warranty")
    @Expose
    private String startWarranty;
    @SerializedName("end_warranty")
    @Expose
    private String endWarranty;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getDurationWarranty() {
        return durationWarranty;
    }

    public void setDurationWarranty(Integer durationWarranty) {
        this.durationWarranty = durationWarranty;
    }

    public Integer getSellerApproval() {
        return sellerApproval;
    }

    public void setSellerApproval(Integer sellerApproval) {
        this.sellerApproval = sellerApproval;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAdminApproval() {
        return adminApproval;
    }

    public void setAdminApproval(Integer adminApproval) {
        this.adminApproval = adminApproval;
    }

    public Integer getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Integer refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getAdminSeen() {
        return adminSeen;
    }

    public void setAdminSeen(Integer adminSeen) {
        this.adminSeen = adminSeen;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getCheckWarranty() {
        return checkWarranty;
    }

    public void setCheckWarranty(String checkWarranty) {
        this.checkWarranty = checkWarranty;
    }

    public String getStartWarranty() {
        return startWarranty;
    }

    public void setStartWarranty(String startWarranty) {
        this.startWarranty = startWarranty;
    }

    public String getEndWarranty() {
        return endWarranty;
    }

    public void setEndWarranty(String endWarranty) {
        this.endWarranty = endWarranty;
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



}

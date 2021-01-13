
package com.onoo.gomlgy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetail2 {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("seller_id")
    @Expose
    private Integer sellerId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("variation")
    @Expose
    private String variation;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("tax")
    @Expose
    private Integer tax;
    @SerializedName("shipping_cost")
    @Expose
    private Integer shippingCost;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("delivery_status")
    @Expose
    private String deliveryStatus;
    @SerializedName("shipping_type")
    @Expose
    private String shippingType;
    @SerializedName("pickup_point_id")
    @Expose
    private Object pickupPointId;
    @SerializedName("product_referral_code")
    @Expose
    private Object productReferralCode;
    @SerializedName("start_warranty")
    @Expose
    private String startWarranty;
    @SerializedName("End_warranty")
    @Expose
    private String endWarranty;
    @SerializedName("warranty_product")
    @Expose
    private Integer warrantyProduct;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("product")
    @Expose
    private ProductPurchases product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Integer shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public Object getPickupPointId() {
        return pickupPointId;
    }

    public void setPickupPointId(Object pickupPointId) {
        this.pickupPointId = pickupPointId;
    }

    public Object getProductReferralCode() {
        return productReferralCode;
    }

    public void setProductReferralCode(Object productReferralCode) {
        this.productReferralCode = productReferralCode;
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

    public Integer getWarrantyProduct() {
        return warrantyProduct;
    }

    public void setWarrantyProduct(Integer warrantyProduct) {
        this.warrantyProduct = warrantyProduct;
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

    public ProductPurchases getProduct() {
        return product;
    }

    public void setProduct(ProductPurchases product) {
        this.product = product;
    }

}

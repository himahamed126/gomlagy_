package com.onoo.gomlgy.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Productmodel implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("added_by")
    @Expose
    private String addedBy;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("sub_category_id")
    @Expose
    private Integer subCategoryId;
    @SerializedName("subsubcategory_id")
    @Expose
    private Integer subsubcategoryId;
    @SerializedName("brand_id")
    @Expose
    private Object brandId;
    @SerializedName("photos")
    @Expose
    private String photos;
    @SerializedName("thumbnail_img")
    @Expose
    private String thumbnailImg;
    @SerializedName("video_provider")
    @Expose
    private String videoProvider;
    @SerializedName("video_link")
    @Expose
    private Object videoLink;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("unit_price")
    @Expose
    private Integer unitPrice;
    @SerializedName("purchase_price")
    @Expose
    private Integer purchasePrice;
    @SerializedName("variant_product")
    @Expose
    private Integer variantProduct;
    @SerializedName("attributes")
    @Expose
    private String attributes;
    @SerializedName("choice_options")
    @Expose
    private String choiceOptions;
    @SerializedName("colors")
    @Expose
    private String colors;
    @SerializedName("variations")
    @Expose
    private Object variations;
    @SerializedName("todays_deal")
    @Expose
    private Integer todaysDeal;
    @SerializedName("published")
    @Expose
    private Integer published;
    @SerializedName("featured")
    @Expose
    private Integer featured;
    @SerializedName("current_stock")
    @Expose
    private Integer currentStock;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("min_qty")
    @Expose
    private Integer minQty;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("discount_type")
    @Expose
    private String discountType;
    @SerializedName("tax")
    @Expose
    private Integer tax;
    @SerializedName("tax_type")
    @Expose
    private String taxType;
    @SerializedName("shipping_type")
    @Expose
    private String shippingType;
    @SerializedName("shipping_cost")
    @Expose
    private Integer shippingCost;
    @SerializedName("num_of_sale")
    @Expose
    private Integer numOfSale;
    @SerializedName("meta_title")
    @Expose
    private String metaTitle;
    @SerializedName("meta_description")
    @Expose
    private Object metaDescription;
    @SerializedName("meta_img")
    @Expose
    private Object metaImg;
    @SerializedName("pdf")
    @Expose
    private Object pdf;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("refundable")
    @Expose
    private Integer refundable;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("barcode")
    @Expose
    private Object barcode;
    @SerializedName("digital")
    @Expose
    private Integer digital;
    @SerializedName("file_name")
    @Expose
    private Object fileName;
    @SerializedName("file_path")
    @Expose
    private Object filePath;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Integer getSubsubcategoryId() {
        return subsubcategoryId;
    }

    public void setSubsubcategoryId(Integer subsubcategoryId) {
        this.subsubcategoryId = subsubcategoryId;
    }

    public Object getBrandId() {
        return brandId;
    }

    public void setBrandId(Object brandId) {
        this.brandId = brandId;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getThumbnailImg() {
        return thumbnailImg;
    }

    public void setThumbnailImg(String thumbnailImg) {
        this.thumbnailImg = thumbnailImg;
    }

    public String getVideoProvider() {
        return videoProvider;
    }

    public void setVideoProvider(String videoProvider) {
        this.videoProvider = videoProvider;
    }

    public Object getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(Object videoLink) {
        this.videoLink = videoLink;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getVariantProduct() {
        return variantProduct;
    }

    public void setVariantProduct(Integer variantProduct) {
        this.variantProduct = variantProduct;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getChoiceOptions() {
        return choiceOptions;
    }

    public void setChoiceOptions(String choiceOptions) {
        this.choiceOptions = choiceOptions;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public Object getVariations() {
        return variations;
    }

    public void setVariations(Object variations) {
        this.variations = variations;
    }

    public Integer getTodaysDeal() {
        return todaysDeal;
    }

    public void setTodaysDeal(Integer todaysDeal) {
        this.todaysDeal = todaysDeal;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public Integer getFeatured() {
        return featured;
    }

    public void setFeatured(Integer featured) {
        this.featured = featured;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getMinQty() {
        return minQty;
    }

    public void setMinQty(Integer minQty) {
        this.minQty = minQty;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public Integer getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Integer shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Integer getNumOfSale() {
        return numOfSale;
    }

    public void setNumOfSale(Integer numOfSale) {
        this.numOfSale = numOfSale;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public Object getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(Object metaDescription) {
        this.metaDescription = metaDescription;
    }

    public Object getMetaImg() {
        return metaImg;
    }

    public void setMetaImg(Object metaImg) {
        this.metaImg = metaImg;
    }

    public Object getPdf() {
        return pdf;
    }

    public void setPdf(Object pdf) {
        this.pdf = pdf;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getRefundable() {
        return refundable;
    }

    public void setRefundable(Integer refundable) {
        this.refundable = refundable;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Object getBarcode() {
        return barcode;
    }

    public void setBarcode(Object barcode) {
        this.barcode = barcode;
    }

    public Integer getDigital() {
        return digital;
    }

    public void setDigital(Integer digital) {
        this.digital = digital;
    }

    public Object getFileName() {
        return fileName;
    }

    public void setFileName(Object fileName) {
        this.fileName = fileName;
    }

    public Object getFilePath() {
        return filePath;
    }

    public void setFilePath(Object filePath) {
        this.filePath = filePath;
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
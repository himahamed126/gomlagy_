
package com.onoo.gomlgy.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product_2 {

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
    @SerializedName("received")
    @Expose
    private Integer received;
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
    private Integer brandId;
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
    private String description;
    @SerializedName("unit_price")
    @Expose
    private Integer unitPrice;
    @SerializedName("min_quantity1")
    @Expose
    private Integer minQuantity1;
    @SerializedName("max_quantity1")
    @Expose
    private Integer maxQuantity1;
    @SerializedName("unit_price2")
    @Expose
    private Integer unitPrice2;
    @SerializedName("min_quantity2")
    @Expose
    private Integer minQuantity2;
    @SerializedName("max_quantity2")
    @Expose
    private Integer maxQuantity2;
    @SerializedName("unit_price3")
    @Expose
    private Integer unitPrice3;
    @SerializedName("min_quantity3")
    @Expose
    private Integer minQuantity3;
    @SerializedName("max_quantity3")
    @Expose
    private Integer maxQuantity3;
    @SerializedName("unit_price_quantity_start")
    @Expose
    private String unitPriceQuantityStart;
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
    private Object tax;
    @SerializedName("tax_type")
    @Expose
    private Object taxType;
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
    private String metaDescription;
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
    @SerializedName("thumbnail_path")
    @Expose
    private List<String> thumbnailPath = null;
    @SerializedName("models")
    @Expose
    private List<Object> models = null;

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

    public Integer getReceived() {
        return received;
    }

    public void setReceived(Integer received) {
        this.received = received;
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

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
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

    public Integer getUnitPrice2() {
        return unitPrice2;
    }

    public void setUnitPrice2(Integer unitPrice2) {
        this.unitPrice2 = unitPrice2;
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

    public Integer getUnitPrice3() {
        return unitPrice3;
    }

    public void setUnitPrice3(Integer unitPrice3) {
        this.unitPrice3 = unitPrice3;
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

    public String getUnitPriceQuantityStart() {
        return unitPriceQuantityStart;
    }

    public void setUnitPriceQuantityStart(String unitPriceQuantityStart) {
        this.unitPriceQuantityStart = unitPriceQuantityStart;
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

    public Object getTax() {
        return tax;
    }

    public void setTax(Object tax) {
        this.tax = tax;
    }

    public Object getTaxType() {
        return taxType;
    }

    public void setTaxType(Object taxType) {
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

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
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

    public List<String> getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(List<String> thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public List<Object> getModels() {
        return models;
    }

    public void setModels(List<Object> models) {
        this.models = models;
    }

}

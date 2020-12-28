
package com.onoo.gomlgy.models.ProductDetails3;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.onoo.gomlgy.models.Brand;
import com.onoo.gomlgy.models.Category;
import com.onoo.gomlgy.models.ChoiceOption;
import com.onoo.gomlgy.models.Links___;
import com.onoo.gomlgy.models.ProductOwner;
import com.onoo.gomlgy.models.SubCategory;

public class ProductDetails3 implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("added_by")
    @Expose
    private String addedBy;
    @SerializedName("user")
    @Expose
    private ProductOwner user;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("sub_category")
    @Expose
    private SubCategory subCategory;
    @SerializedName("brand")
    @Expose
    private Brand brand;
    @SerializedName("photos")
    @Expose
    private List<String> photos = null;
    @SerializedName("thumbnail_image")
    @Expose
    private String thumbnailImage;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("price_lower")
    @Expose
    private Integer priceLower;
    @SerializedName("price_higher")
    @Expose
    private Integer priceHigher;
    @SerializedName("choice_options")
    @Expose
    private List<ChoiceOption> choiceOptions = null;
    @SerializedName("colors")
    @Expose
    private List<String> colors = null;
    @SerializedName("todays_deal")
    @Expose
    private Integer todaysDeal;
    @SerializedName("featured")
    @Expose
    private Integer featured;
    @SerializedName("current_stock")
    @Expose
    private Integer currentStock;
    @SerializedName("unit")
    @Expose
    private String unit;
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
    private Object taxType;
    @SerializedName("shipping_type")
    @Expose
    private Object shippingType;
    @SerializedName("shipping_cost")
    @Expose
    private Integer shippingCost;
    @SerializedName("number_of_sales")
    @Expose
    private Integer numberOfSales;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("rating_count")
    @Expose
    private Integer ratingCount;
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
    @SerializedName("product_stocks")
    @Expose
    private List<ProductStock> productStocks = null;
    @SerializedName("links")
    @Expose
    private Links___ links;

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

    public ProductOwner getUser() {
        return user;
    }

    public void setUser(ProductOwner user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getPriceLower() {
        return priceLower;
    }

    public void setPriceLower(Integer priceLower) {
        this.priceLower = priceLower;
    }

    public Integer getPriceHigher() {
        return priceHigher;
    }

    public void setPriceHigher(Integer priceHigher) {
        this.priceHigher = priceHigher;
    }

    public List<ChoiceOption> getChoiceOptions() {
        return choiceOptions;
    }

    public void setChoiceOptions(List<ChoiceOption> choiceOptions) {
        this.choiceOptions = choiceOptions;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
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

    public Object getTaxType() {
        return taxType;
    }

    public void setTaxType(Object taxType) {
        this.taxType = taxType;
    }

    public Object getShippingType() {
        return shippingType;
    }

    public void setShippingType(Object shippingType) {
        this.shippingType = shippingType;
    }

    public Integer getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Integer shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Integer getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(Integer numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
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

    public List<ProductStock> getProductStocks() {
        return productStocks;
    }

    public void setProductStocks(List<ProductStock> productStocks) {
        this.productStocks = productStocks;
    }

    public Links___ getLinks() {
        return links;
    }

    public void setLinks(Links___ links) {
        this.links = links;
    }

}

package com.onoo.gomlgy.Network.response;

import com.onoo.gomlgy.models.Meta;
import com.onoo.gomlgy.models.Product;
import com.onoo.gomlgy.models.ProductLisitingLink;
import com.onoo.gomlgy.models.SearchProduct;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductSearchResponse {
    @SerializedName("data")
    @Expose
    private List<Product> data = null;
    @SerializedName("links")
    @Expose
    private ProductLisitingLink links;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("status")
    @Expose
    private Integer status;

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    public ProductLisitingLink getLinks() {
        return links;
    }

    public void setLinks(ProductLisitingLink links) {
        this.links = links;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

package com.onoo.gomlgy.Network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.onoo.gomlgy.Models.ProductDetails2;

import java.util.List;

public class ProductDetailsResponse2 {

    @SerializedName("data")
    @Expose
    private List<ProductDetails2> data = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("status")
    @Expose
    private Integer status;

    public List<ProductDetails2> getData() {
        return data;
    }

    public void setData(List<ProductDetails2> data) {
        this.data = data;
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

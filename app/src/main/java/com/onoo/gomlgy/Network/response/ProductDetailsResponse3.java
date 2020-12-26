
package com.onoo.gomlgy.Network.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.onoo.gomlgy.models.ProductDetails3.ProductDetails3;

public class ProductDetailsResponse3 {

    @SerializedName("data")
    @Expose
    private List<ProductDetails3> data = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("status")
    @Expose
    private Integer status;

    public List<ProductDetails3> getData() {
        return data;
    }

    public void setData(List<ProductDetails3> data) {
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

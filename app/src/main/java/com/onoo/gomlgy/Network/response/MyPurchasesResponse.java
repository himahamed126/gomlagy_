
package com.onoo.gomlgy.Network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.onoo.gomlgy.models.PurchasesPagination;

public class MyPurchasesResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private PurchasesPagination data;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public PurchasesPagination getData() {
        return data;
    }

    public void setPurchasesPagination(PurchasesPagination data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

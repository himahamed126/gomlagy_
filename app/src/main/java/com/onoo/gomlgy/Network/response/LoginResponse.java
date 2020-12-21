package com.onoo.gomlgy.Network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.onoo.gomlgy.models.User;

public class LoginResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("is_verified")
    @Expose
    private Integer isVerified;
    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("user")
    @Expose
    private User user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public User getData() {
        return user;
    }

    public void setData(User user) {
        this.user = user;
    }
}

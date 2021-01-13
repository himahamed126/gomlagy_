package com.onoo.gomlgy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Message {
    @SerializedName("reason")
    @Expose
    private List<String> reason = null;

    public List<String> getReason() {
        return reason;
    }

    public void setReason(List<String> reason) {
        this.reason = reason;
    }

}

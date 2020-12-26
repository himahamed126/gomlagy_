package com.onoo.gomlgy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubCategory implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("subSubCategories")
    @Expose
    private SubSubCategories subSubCategories;
    @SerializedName("links")
    @Expose
    private Link links;

    public SubCategory() {
    }

    public SubCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubSubCategories getSubSubCategories() {
        return subSubCategories;
    }

    public void SubSubCategories(SubSubCategories subSubCategories) {
        this.subSubCategories = subSubCategories;
    }

    public Link getLinks() {
        return links;
    }

    public void setLinks(Link links) {
        this.links = links;
    }

}

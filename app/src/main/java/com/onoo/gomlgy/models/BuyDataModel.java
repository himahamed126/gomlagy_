package com.onoo.gomlgy.models;

public class BuyDataModel {

    private int id;
    private String color;
    private String modelName;
    private int quantity;
    private int changeQuantity;
    private double price;
    private double changePrice;

    public BuyDataModel(int id, String color, String modelName, int quantity, int changeQuantity, double price, double changePrice) {
        this.id = id;
        this.color = color;
        this.modelName = modelName;
        this.quantity = quantity;
        this.price = price;
        this.changePrice = changePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getChangeQuantity() {
        return changeQuantity;
    }

    public void setChangeQuantity(int changeQuantity) {
        this.changeQuantity = changeQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(double changePrice) {
        this.changePrice = changePrice;
    }

}

package com.demoblaze.testdata;

public class ProductData {
    private String title;
    private String price;
    private String description;

    public ProductData(String title, String price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    // ACCESSORS
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
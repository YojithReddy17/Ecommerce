package com.ecommerce.model;

import java.io.Serializable;

public class Product implements Serializable {
    private long productId;
    private String name;
    private String description;
    private double price;

    public Product(long productId, String name, String description, double price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getters and Setters
    public long getProductId() { return productId; }
    public void setProductId(long productId) { this.productId = productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Product #" + productId + ": " + name + " - $" + String.format("%.2f", price);
    }
}
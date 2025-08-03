package com.ecommerce.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Serializable {
    private long userId;
    private List<CartItem> items;

    public ShoppingCart(long userId) {
        this.userId = userId;
        this.items = new ArrayList<>();
    }

    // Getters and Setters
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
}
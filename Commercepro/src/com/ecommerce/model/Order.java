package com.ecommerce.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Order implements Serializable {
    private long orderId;
    private long userId;
    private List<CartItem> orderedItems;
    private transient LocalDateTime orderDate; // Marked transient
    private double totalPrice;

    public Order(long orderId, long userId, List<CartItem> orderedItems, double totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderedItems = orderedItems;
        this.totalPrice = totalPrice;
        this.orderDate = LocalDateTime.now();
    }

    //Getters and Setters
    public long getOrderId() { return orderId; }
    public void setOrderId(long orderId) { this.orderId = orderId; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public List<CartItem> getOrderedItems() { return orderedItems; }
    public void setOrderedItems(List<CartItem> orderedItems) { this.orderedItems = orderedItems; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
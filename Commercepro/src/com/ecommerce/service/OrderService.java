package com.ecommerce.service;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.Order;
import com.ecommerce.model.ShoppingCart;
import com.ecommerce.repository.OrderRepository;
import java.util.ArrayList;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(ShoppingCart cart) {
        if (cart == null || cart.getItems().isEmpty()) {
            return null;
        }

        double totalPrice = cart.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        Order newOrder = new Order(
                System.currentTimeMillis(),
                cart.getUserId(),
                new ArrayList<>(cart.getItems()),
                totalPrice
        );

        orderRepository.save(newOrder);
        return newOrder;
    }
}
package com.ecommerce.repository;

import com.ecommerce.model.Order;
import java.util.List;

public interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
}
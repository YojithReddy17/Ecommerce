package com.ecommerce.service;

import com.ecommerce.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private final List<Product> products;

    public ProductService() {
        this.products = new ArrayList<>();
        products.add(new Product(1, "Laptop", "High-performance laptop", 1200.00));
        products.add(new Product(2, "Mouse", "Ergonomic wireless mouse", 75.50));
        products.add(new Product(3, "Keyboard", "Mechanical keyboard", 150.00));
        products.add(new Product(4, "Webcam", "HD 1080p Webcam", 80.00));
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products); // Return a copy
    }

    public Optional<Product> findById(long productId) {
        return products.stream()
                .filter(p -> p.getProductId() == productId)
                .findFirst();
    }
}
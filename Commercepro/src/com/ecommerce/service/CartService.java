package com.ecommerce.service;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import com.ecommerce.model.ShoppingCart;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CartService {
    private final Map<Long, ShoppingCart> userCarts = new HashMap<>();

    public ShoppingCart getCart(long userId) {
        return userCarts.computeIfAbsent(userId, ShoppingCart::new);
    }

    public void addProductToCart(long userId, Product product, int quantity) {
        ShoppingCart cart = getCart(userId);

        Optional<CartItem> existingItemOpt = cart.getItems().stream()
                .filter(item -> item.getProduct().getProductId() == product.getProductId())
                .findFirst();

        if (existingItemOpt.isPresent()) {
            CartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            cart.getItems().add(new CartItem(product, quantity));
        }
    }

    public void clearCart(long userId) {
        ShoppingCart cart = getCart(userId);
        if (cart != null) {
            cart.getItems().clear();
        }
    }
}
package com.ecommerce.repository;

import com.ecommerce.model.User;

public interface UserRepository {
    User findByUsername(String username);
    void save(User user);
}
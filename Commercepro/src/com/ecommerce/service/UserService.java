package com.ecommerce.service;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            return null;
        }
        User newUser = new User(System.currentTimeMillis(), username, password);
        userRepository.save(newUser);
        return newUser;
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getHashedPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
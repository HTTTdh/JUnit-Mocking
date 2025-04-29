package com.example.test.services;

import com.example.test.models.User;
import com.example.test.repositories.UserRepository;

// UserService.java
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserName(int id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return user.getName();
    }

    public void registerUser(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Invalid user name");
        }
        userRepository.save(user);
    }
}

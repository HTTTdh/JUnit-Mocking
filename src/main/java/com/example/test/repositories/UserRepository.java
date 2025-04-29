package com.example.test.repositories;

import com.example.test.models.User;

public interface UserRepository {
    User findById(int id);
    void save(User user);
}
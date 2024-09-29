package com.example.oirms.service;

import com.example.oirms.model.User;

public interface UserService {
    void registerUser(User user);
    User getUserByEmail(String email);
    User getUserById(Long userId);
}

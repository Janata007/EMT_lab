package com.example.library.service;

import com.example.library.models.User;

public interface AuthService {
    User login(String username, String password);
}

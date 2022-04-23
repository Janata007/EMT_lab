package com.example.library.service.impl;

import com.example.library.models.User;
import com.example.library.repository.UserRepository;
import com.example.library.service.AuthService;
import com.example.library.service.exceptions.InvalidArgumentsException;
import com.example.library.service.exceptions.InvalidUserCredentialsException;

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}

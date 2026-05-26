package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

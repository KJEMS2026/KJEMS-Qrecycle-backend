package com.example.kjemsqrecyclebackend.controller;

import com.example.kjemsqrecyclebackend.service.IUserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private IUserService userService;

    /*
    public UserController(IUserService userService) {
        this.userService = userService;
    }

     */
}

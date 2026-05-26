package com.example.kjemsqrecyclebackend.controller;

import com.example.kjemsqrecyclebackend.dto.UserDTO;
import com.example.kjemsqrecyclebackend.entity.User;
import com.example.kjemsqrecyclebackend.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping ("/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody UserCreationDTO body){

        User created = userService.saveUser(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}

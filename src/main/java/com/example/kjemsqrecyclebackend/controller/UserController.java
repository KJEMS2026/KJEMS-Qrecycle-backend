package com.example.kjemsqrecyclebackend.controller;

import com.example.kjemsqrecyclebackend.dto.UserCreationDTO;
import com.example.kjemsqrecyclebackend.dto.UserDTO;
import com.example.kjemsqrecyclebackend.dto.UserEditDTO;
import com.example.kjemsqrecyclebackend.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<UserCreationDTO> saveUser(@RequestBody UserCreationDTO body){

        UserCreationDTO created = userService.saveUser(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/updateUser/{id}")
    public ResponseEntity<UserEditDTO> getPrefilledUserForEditForm(@PathVariable UUID id){
        return ResponseEntity.ok(userService.getPrefilledUserForEditForm(id));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable UUID id, @RequestBody UserEditDTO dto){

        userService.updateUser(id, dto);

        return ResponseEntity.ok().build();
    }

}

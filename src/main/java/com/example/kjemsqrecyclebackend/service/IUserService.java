package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.UserCreationDTO;
import com.example.kjemsqrecyclebackend.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    List<UserDTO> getAllUsers();
    UserCreationDTO saveUser(UserCreationDTO dto);

}

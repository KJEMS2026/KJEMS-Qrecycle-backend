package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.UserCreationDTO;
import com.example.kjemsqrecyclebackend.dto.UserDTO;
import com.example.kjemsqrecyclebackend.dto.UserEditDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IUserService {

    List<UserDTO> getAllUsers();
    UserCreationDTO saveUser(UserCreationDTO dto);
    void deleteAuthUser(UUID id);
    void updateUser(UUID id, UserEditDTO dto);
    UserEditDTO getPrefilledUserForEditForm(UUID id);

}

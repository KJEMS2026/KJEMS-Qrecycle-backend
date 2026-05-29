package com.example.kjemsqrecyclebackend.dto;

import com.example.kjemsqrecyclebackend.entity.Company;
import com.example.kjemsqrecyclebackend.entity.UserRole;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String fullName;
    private String email;
    private UserRole role;
    private String company;

}

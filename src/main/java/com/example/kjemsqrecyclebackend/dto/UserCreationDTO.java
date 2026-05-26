package com.example.kjemsqrecyclebackend.dto;

import com.example.kjemsqrecyclebackend.entity.UserRole;
import lombok.Data;

@Data
public class UserCreationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phonenumber;
    private UserRole role;
    private String companyName;
    private String companyAddress;

}

package com.example.kjemsqrecyclebackend.dto;

import lombok.Data;

@Data
public class UserEditDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phonenumber;
    private String companyName;
    private String companyAddress;
}

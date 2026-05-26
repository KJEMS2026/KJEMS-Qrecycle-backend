package com.example.kjemsqrecyclebackend.dto;

import lombok.Data;

@Data
public class CompanyUserDTO {
    private int companyId;
    private String companyName;
    private String address;

    private String fullName;
    private String email;
    private String phone;
}

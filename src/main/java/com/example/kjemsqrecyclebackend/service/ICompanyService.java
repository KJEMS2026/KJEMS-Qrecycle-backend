package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.CompanyDTO;
import com.example.kjemsqrecyclebackend.dto.CompanyUserDTO;
import com.example.kjemsqrecyclebackend.dto.UserCreationDTO;
import com.example.kjemsqrecyclebackend.dto.UserEditDTO;
import com.example.kjemsqrecyclebackend.entity.Company;
import com.example.kjemsqrecyclebackend.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ICompanyService {
    List<CompanyDTO> getCompanies();
    List<CompanyUserDTO> getCompaniesAndCompanyUsers();
    Company saveCompany(UserCreationDTO dto, User user);
    void updateCompany(UserEditDTO dto,  User user);
    void getPrefilledCompanyForEditForm(UUID userId, UserEditDTO dto);
}

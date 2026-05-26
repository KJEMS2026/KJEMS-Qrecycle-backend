package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.CompanyDTO;
import com.example.kjemsqrecyclebackend.dto.CompanyUserDTO;
import com.example.kjemsqrecyclebackend.dto.UserCreationDTO;
import com.example.kjemsqrecyclebackend.entity.Company;
import com.example.kjemsqrecyclebackend.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICompanyService {
    List<CompanyDTO> getCompanies();
    List<CompanyUserDTO> getCompaniesAndCompanyUsers();
    Company saveCompany(UserCreationDTO dto, User user);
}

package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.CompanyDTO;
import com.example.kjemsqrecyclebackend.dto.CompanyUserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICompanyService {
    List<CompanyDTO> getCompanies();
    List<CompanyUserDTO> getCompaniesAndCompanyUsers();
}

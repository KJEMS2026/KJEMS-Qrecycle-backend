package com.example.kjemsqrecyclebackend.controller;

import com.example.kjemsqrecyclebackend.dto.CompanyDTO;
import com.example.kjemsqrecyclebackend.dto.CompanyUserDTO;
import com.example.kjemsqrecyclebackend.service.ICompanyService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CompanyController {

    private ICompanyService companyService;

    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public List<CompanyDTO> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/companies-users")
    public List<CompanyUserDTO> getCompaniesAndCompanyUsers() {
        return companyService.getCompaniesAndCompanyUsers();
    }
}

package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements ICompanyService{

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
}

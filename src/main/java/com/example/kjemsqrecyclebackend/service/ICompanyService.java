package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.CompanyDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICompanyService {
    List<CompanyDTO> getCompanies();
}

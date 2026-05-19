package com.example.kjemsqrecyclebackend.config;

import com.example.kjemsqrecyclebackend.repository.CompanyRepository;
import com.example.kjemsqrecyclebackend.service.CompanyService;
import com.example.kjemsqrecyclebackend.service.ICompanyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompanyServiceConfig {

    @Bean
    public ICompanyService iCompanyService(CompanyRepository companyRepository) {
        return new CompanyService(companyRepository);
    }
}

package com.example.kjemsqrecyclebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public void run(String... args) throws Exception{
        //findall
        Company company1 = companyRepository.findById(1).orElseThrow();
        company1.setCompanyName("Harboøre Tange");
        company1.setAddress("Tange vej 123");

        companyRepository.save(company1);
        List<Company> company2 = companyRepository.findAll();
        System.out.println(company2);

    }
}

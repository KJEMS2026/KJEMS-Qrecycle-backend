package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.CompanyUserDTO;
import com.example.kjemsqrecyclebackend.dto.CompanyDTO;
import com.example.kjemsqrecyclebackend.dto.UserCreationDTO;
import com.example.kjemsqrecyclebackend.entity.Company;
import com.example.kjemsqrecyclebackend.entity.User;
import com.example.kjemsqrecyclebackend.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService implements ICompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    @Transactional
    public List<CompanyDTO> getCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDTO> companiesDTO = new ArrayList<>();

        for(Company company : companies) {
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setId(company.getId());
            companyDTO.setName(company.getCompanyName());
            companiesDTO.add(companyDTO);
        }
        return companiesDTO;
    }

    @Override
    @Transactional
    public List<CompanyUserDTO> getCompaniesAndCompanyUsers() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyUserDTO> companyUserDTOList = new ArrayList<>();

        for (Company company : companies) {
            CompanyUserDTO dto = new CompanyUserDTO();
            dto.setCompanyId(company.getId());
            dto.setCompanyName(company.getCompanyName());
            dto.setAddress(company.getAddress());
            String fullName = company.getUser().getFirstName() + " " + company.getUser().getLastName();
            dto.setFullName(fullName);
            dto.setEmail(company.getUser().getEmail());
            dto.setPhone(company.getUser().getPhonenumber());
            companyUserDTOList.add(dto);
        }
        return companyUserDTOList;
    }

    public Company saveCompany(UserCreationDTO dto, User user){
        Company company = new Company();
        company.setCompanyName(dto.getCompanyName());
        company.setAddress(dto.getCompanyAddress());
        company.setUser(user);
        return companyRepository.save(company);
    }
}

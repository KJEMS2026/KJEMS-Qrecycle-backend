package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.UserDTO;
import com.example.kjemsqrecyclebackend.entity.Company;
import com.example.kjemsqrecyclebackend.entity.User;
import com.example.kjemsqrecyclebackend.repository.CompanyRepository;
import com.example.kjemsqrecyclebackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService{

    private UserRepository userRepository;
    private CompanyRepository companyRepository;

    public UserService(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDTO> dtoUsers = new ArrayList<>();

        for(User user : users){
            UserDTO userDTO = new UserDTO();
            String fullName = user.getFirstName() + " " + user.getLastName();
            userDTO.setFullName(fullName);
            userDTO.setEmail(user.getEmail());
            userDTO.setRole(user.getRole());
            Company company = companyRepository.findByUserId(user.getId());
            userDTO.setCompany(company != null ? company.getCompanyName() : "-");
            dtoUsers.add(userDTO);
        }
        return dtoUsers;
    }
}

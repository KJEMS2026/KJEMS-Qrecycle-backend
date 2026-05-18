package com.example.kjemsqrecyclebackend.repository;

import com.example.kjemsqrecyclebackend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

}

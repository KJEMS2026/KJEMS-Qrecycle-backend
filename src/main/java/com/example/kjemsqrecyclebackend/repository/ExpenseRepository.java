package com.example.kjemsqrecyclebackend.repository;

import com.example.kjemsqrecyclebackend.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}

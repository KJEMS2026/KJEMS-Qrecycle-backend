package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService implements IExpenseService {

    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
}

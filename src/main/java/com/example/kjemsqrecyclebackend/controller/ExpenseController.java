package com.example.kjemsqrecyclebackend.controller;

import com.example.kjemsqrecyclebackend.service.IDriverService;
import com.example.kjemsqrecyclebackend.service.IExpenseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ExpenseController {

    private IExpenseService expenseService;

    public ExpenseController(IExpenseService expenseService) {
        this.expenseService = expenseService;
    }
}

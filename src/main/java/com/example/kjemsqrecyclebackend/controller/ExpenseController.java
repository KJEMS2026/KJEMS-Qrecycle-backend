package com.example.kjemsqrecyclebackend.controller;

import com.example.kjemsqrecyclebackend.dto.ExpenseDTO;
import com.example.kjemsqrecyclebackend.service.IExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ExpenseController {

    private IExpenseService expenseService;

    public ExpenseController(IExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses")
    public List<ExpenseDTO> getExpenses() {
        return expenseService.getExpenses();
    }

    @PostMapping("/create/expense")
    public ResponseEntity<ExpenseDTO> saveExpense(@RequestBody ExpenseDTO body) {

        ExpenseDTO created = expenseService.saveExpense(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}

package com.example.kjemsqrecyclebackend.controller;

import com.example.kjemsqrecyclebackend.dto.ExpenseRegisterDTO;
import com.example.kjemsqrecyclebackend.dto.ExpenseStatsDTO;
import com.example.kjemsqrecyclebackend.service.IExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class ExpenseController {

    private IExpenseService expenseService;

    public ExpenseController(IExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses")
    public List<ExpenseStatsDTO> getExpenses() {
        return expenseService.getExpenses();
    }

    @PostMapping("/create/expense/{driverId}")
    public ResponseEntity<ExpenseRegisterDTO> saveExpense(@RequestBody ExpenseRegisterDTO body, @PathVariable UUID driverId) {

        ExpenseRegisterDTO created = expenseService.saveExpense(body, driverId);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}

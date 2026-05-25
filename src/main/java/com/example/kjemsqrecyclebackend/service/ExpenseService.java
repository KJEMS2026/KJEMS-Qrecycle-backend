package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.ExpenseDTO;
import com.example.kjemsqrecyclebackend.entity.Expense;
import com.example.kjemsqrecyclebackend.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService implements IExpenseService {

    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<ExpenseDTO> getExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        List<ExpenseDTO> expensesDTO = new ArrayList<>();

        for (Expense expense : expenses) {
            ExpenseDTO expenseDTO = new ExpenseDTO();
            expenseDTO.setImage(expense.getImage());
            expenseDTO.setTitle(expense.getTitle());
            expenseDTO.setDescription(expense.getDescription());
            expenseDTO.setCreationDate(expense.getCreationDate());
            expenseDTO.setCreatedBy(expense.getCreatedBy());
            expensesDTO.add(expenseDTO);
        }
        return expensesDTO;
    }

    public ExpenseDTO saveExpense(ExpenseDTO expenseDTO) {
        Expense expense = new Expense();
        expense.setImage(expenseDTO.getImage());
        expense.setTitle(expenseDTO.getTitle());
        expense.setDescription(expenseDTO.getDescription());
        expense.setCreationDate(LocalDateTime.now());
        expense.setCreatedBy(expenseDTO.getCreatedBy());
        expenseRepository.save(expense);
        expenseDTO.setCreationDate(expense.getCreationDate());
        return expenseDTO;
    }
}

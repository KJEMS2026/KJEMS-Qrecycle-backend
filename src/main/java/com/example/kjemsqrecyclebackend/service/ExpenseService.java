package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.ExpenseRegisterDTO;
import com.example.kjemsqrecyclebackend.dto.ExpenseStatsDTO;
import com.example.kjemsqrecyclebackend.entity.Expense;
import com.example.kjemsqrecyclebackend.entity.User;
import com.example.kjemsqrecyclebackend.repository.ExpenseRepository;
import com.example.kjemsqrecyclebackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService implements IExpenseService {

    private ExpenseRepository expenseRepository;
    private UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<ExpenseStatsDTO> getExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        List<ExpenseStatsDTO> expensesDTO = new ArrayList<>();

        for (Expense expense : expenses) {
            ExpenseStatsDTO expenseDTO = new ExpenseStatsDTO();
            expenseDTO.setImage(expense.getImage());
            expenseDTO.setTitle(expense.getTitle());
            expenseDTO.setDescription(expense.getDescription());
            expenseDTO.setCreationDate(expense.getCreationDate());
            String fullName = expense.getCreatedBy().getFirstName() + " " + expense.getCreatedBy().getLastName();
            expenseDTO.setCreatedBy(fullName);
            expensesDTO.add(expenseDTO);
        }
        return expensesDTO;
    }

    @Override
    @Transactional
    public ExpenseRegisterDTO saveExpense(ExpenseRegisterDTO expenseDTO, UUID driverId) {
        User driver = userRepository.findById(driverId).orElseThrow();
        Expense expense = new Expense();
        expense.setImage(expenseDTO.getImage());
        expense.setTitle(expenseDTO.getTitle());
        expense.setDescription(expenseDTO.getDescription());
        expense.setCreationDate(LocalDateTime.now());
        expense.setCreatedBy(driver);
        expenseRepository.save(expense);
        expenseDTO.setCreationDate(expense.getCreationDate());
        return expenseDTO;
    }
}

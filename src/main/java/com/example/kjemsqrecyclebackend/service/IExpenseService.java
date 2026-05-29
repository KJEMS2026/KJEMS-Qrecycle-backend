package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.ExpenseRegisterDTO;
import com.example.kjemsqrecyclebackend.dto.ExpenseStatsDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IExpenseService {
    List<ExpenseStatsDTO> getExpenses();
    ExpenseRegisterDTO saveExpense(ExpenseRegisterDTO expenseDTO, UUID driverId);
}

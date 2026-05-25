package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.ExpenseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IExpenseService {
    List<ExpenseDTO> getExpenses();
    ExpenseDTO saveExpense(ExpenseDTO expenseDTO);
}

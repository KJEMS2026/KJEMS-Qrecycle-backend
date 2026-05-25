package com.example.kjemsqrecyclebackend.dto;

import com.example.kjemsqrecyclebackend.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpenseDTO {
    private String image;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private User createdBy;
}

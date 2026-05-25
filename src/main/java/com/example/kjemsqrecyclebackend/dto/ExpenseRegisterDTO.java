package com.example.kjemsqrecyclebackend.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ExpenseRegisterDTO {
    private UUID id;
    private String image;
    private String title;
    private String description;
    private LocalDateTime creationDate;
}

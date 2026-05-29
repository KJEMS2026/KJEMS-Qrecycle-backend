package com.example.kjemsqrecyclebackend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpenseStatsDTO {
    private String image;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private String createdBy;
}

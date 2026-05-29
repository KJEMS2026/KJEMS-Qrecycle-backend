package com.example.kjemsqrecyclebackend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
public class ActivePickupRequestDTO {
    private int id;
    private String companyName;
    private LocalDateTime createdAt;
    private int bagsToBeCollected;
}

package com.example.kjemsqrecyclebackend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatsDTO {
    private LocalDateTime dateCollected;
    private String companyName;
    private int  bagsToBeCollected;
    private int bagsCollected;
    private int differenceInBags;
    private String fullName;
}

package com.example.kjemsqrecyclebackend.dto;


import com.example.kjemsqrecyclebackend.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterPantDTO {
    private int pickupRequestId;
    private int bagsCollected;
    private LocalDateTime dateCollected;
}

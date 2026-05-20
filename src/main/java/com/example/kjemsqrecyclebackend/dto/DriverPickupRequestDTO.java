package com.example.kjemsqrecyclebackend.dto;

import com.example.kjemsqrecyclebackend.entity.Company;
import com.example.kjemsqrecyclebackend.entity.User;

import java.time.LocalDateTime;


public class DriverPickupRequestDTO {

    private int pickupRequestId;
    private int bagsPickedUp;
    private int bagsForPickup;
    private int differenceInBags;
    private LocalDateTime creationDate;
    private LocalDateTime pickupDate;
    private Company company;
    private User pickedUpBy;

}

package com.example.kjemsqrecyclebackend.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminPickupRequestDTO {

    private int bagsToBeCollected;
    private int companyId;

}
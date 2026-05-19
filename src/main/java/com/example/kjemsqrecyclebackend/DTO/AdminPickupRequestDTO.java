package com.example.kjemsqrecyclebackend.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminPickupRequestDTO {

    private Long bagsToBeCollected;
    private Long companyId;

}
package com.example.kjemsqrecyclebackend.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CompanyPickupRequestDTO {

    private int activePickupRequestId;
    private UUID userId;
    private int bagsToBeCollected;

}

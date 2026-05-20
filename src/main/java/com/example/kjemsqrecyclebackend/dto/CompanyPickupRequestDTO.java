package com.example.kjemsqrecyclebackend.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CompanyPickupRequestDTO {

    private UUID userId;
    private Long bagsToBeCollected;

}

package com.example.kjemsqrecyclebackend.DTOMapper;


import com.example.kjemsqrecyclebackend.dto.DriverPickupRequestDTO;
import com.example.kjemsqrecyclebackend.entity.PickupRequest;

public class PickupRequestMapper {

    public PickupRequest toEntity(DriverPickupRequestDTO companyPickupRequestDTO) {
        PickupRequest pR = new PickupRequest();
        pR.setBagsToBeCollected(companyPickupRequestDTO.getBagsForPickup());
        pR.setBagsCollected(companyPickupRequestDTO.getBagsPickedUp());
        pR.setDateCreation(companyPickupRequestDTO.getCreationDate());
        pR.setDateCollected(companyPickupRequestDTO.getPickupDate());
        pR.setUser(companyPickupRequestDTO.getPickedUpBy());
        pR.setCompany(companyPickupRequestDTO.getCompany());
        pR.setId(companyPickupRequestDTO.getPickupRequestId());
        return pR;
    }
}

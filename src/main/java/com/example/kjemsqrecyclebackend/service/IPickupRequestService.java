package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.AdminPickupRequestDTO;
import com.example.kjemsqrecyclebackend.dto.CompanyPickupRequestDTO;
import com.example.kjemsqrecyclebackend.entity.PickupRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IPickupRequestService {
    PickupRequest createForCompany(UUID authUserId, CompanyPickupRequestDTO dto);
    PickupRequest createForAdmin(AdminPickupRequestDTO dto);
}

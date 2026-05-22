package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.ActivePickupRequestDTO;
import com.example.kjemsqrecyclebackend.dto.RegisterPantDTO;
import com.example.kjemsqrecyclebackend.entity.PickupRequest;
import com.example.kjemsqrecyclebackend.dto.AdminPickupRequestDTO;
import com.example.kjemsqrecyclebackend.dto.CompanyPickupRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.UUID;

@Service
public interface IPickupRequestService {

    List<ActivePickupRequestDTO> getActivePickupRequests();
    List<CompanyPickupRequestDTO> getActivePickupRequestsCompany(UUID userId);

    PickupRequest createForCompany(UUID authUserId, CompanyPickupRequestDTO dto);
    PickupRequest createForAdmin(AdminPickupRequestDTO dto);
    PickupRequest updatePickupRequest(RegisterPantDTO dto, UUID driverId);
}

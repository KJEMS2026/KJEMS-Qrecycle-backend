package com.example.kjemsqrecyclebackend.controller;

import com.example.kjemsqrecyclebackend.DTO.AdminPickupRequestDTO;
import com.example.kjemsqrecyclebackend.DTO.CompanyPickupRequestDTO;
import com.example.kjemsqrecyclebackend.entity.PickupRequest;
import com.example.kjemsqrecyclebackend.service.IPickupRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class PickupRequestController {

    private final IPickupRequestService pickupRequestService;

    public PickupRequestController(IPickupRequestService pickupRequestService) {
        this.pickupRequestService = pickupRequestService;
    }

    @PostMapping("/pickup-requests/company")
    public ResponseEntity<PickupRequest> createForCompany(
            @RequestBody CompanyPickupRequestDTO dto,
            Authentication auth) {

        UUID authUserId = UUID.fromString(auth.getName());

        PickupRequest created = pickupRequestService.createForCompany(authUserId, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/pickup-requests/admin")
    public ResponseEntity<PickupRequest> createForAdmin(
            @RequestBody AdminPickupRequestDTO dto) {

        PickupRequest created = pickupRequestService.createForAdmin(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}

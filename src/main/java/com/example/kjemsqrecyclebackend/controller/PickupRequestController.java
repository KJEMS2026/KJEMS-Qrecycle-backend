package com.example.kjemsqrecyclebackend.controller;

import com.example.kjemsqrecyclebackend.dto.AdminPickupRequestDTO;
import com.example.kjemsqrecyclebackend.dto.CompanyPickupRequestDTO;
import com.example.kjemsqrecyclebackend.dto.RegisterPantDTO;
import com.example.kjemsqrecyclebackend.entity.PickupRequest;
import com.example.kjemsqrecyclebackend.dto.ActivePickupRequestDTO;
import com.example.kjemsqrecyclebackend.service.IPickupRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
            @RequestBody CompanyPickupRequestDTO dto) {

        PickupRequest created = pickupRequestService.createForCompany(dto.getUserId(), dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/pickup-requests/admin")
    public ResponseEntity<PickupRequest> createForAdmin(
            @RequestBody AdminPickupRequestDTO dto) {

        PickupRequest created = pickupRequestService.createForAdmin(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/active-pickup-requests")
    public List<ActivePickupRequestDTO> getActivePickupRequests() {
        return pickupRequestService.getActivePickupRequests();
    }

    @PostMapping("/update-pickuprequest/{driverId}")
    public ResponseEntity<PickupRequest> updatePickupRequest(@RequestBody RegisterPantDTO body, @PathVariable UUID driverId){
        PickupRequest updated = pickupRequestService.updatePickupRequest(body,driverId);

        return ResponseEntity.status(HttpStatus.CREATED).body(updated);
    }
}

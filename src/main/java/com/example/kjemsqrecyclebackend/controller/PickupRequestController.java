package com.example.kjemsqrecyclebackend.controller;

import com.example.kjemsqrecyclebackend.dto.ActivePickupRequestDTO;
import com.example.kjemsqrecyclebackend.service.IPickupRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PickupRequestController {

    private IPickupRequestService pickupRequestService;

    public PickupRequestController(IPickupRequestService pickupRequestService) {
        this.pickupRequestService = pickupRequestService;
    }

    @GetMapping("/active-pickup-requests")
    public List<ActivePickupRequestDTO> getActivePickupRequests() {
        return pickupRequestService.getActivePickupRequests();
    }
}

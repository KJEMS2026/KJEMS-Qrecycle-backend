package com.example.kjemsqrecyclebackend.controller;

import com.example.kjemsqrecyclebackend.service.IPickupRequestService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class PickupRequestController {

    private IPickupRequestService pickupRequestService;

    public PickupRequestController(IPickupRequestService pickupRequestService) {
        this.pickupRequestService = pickupRequestService;
    }
}

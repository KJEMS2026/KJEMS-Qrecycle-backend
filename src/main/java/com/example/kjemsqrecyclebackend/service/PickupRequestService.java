package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.entity.PickupRequest;
import com.example.kjemsqrecyclebackend.repository.PickupRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class PickupRequestService {

    private PickupRequestRepository pickupRequestRepository;

    public PickupRequestService(PickupRequestRepository pickupRequestRepository) {
        this.pickupRequestRepository = pickupRequestRepository;
    }

    public void saveFinishedPickupRequest(PickupRequest pickupRequest) {
        pickupRequestRepository.save(pickupRequest);
    }




}

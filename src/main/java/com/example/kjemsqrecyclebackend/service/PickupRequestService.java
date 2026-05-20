package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.ActivePickupRequestDTO;
import com.example.kjemsqrecyclebackend.entity.PickupRequest;
import com.example.kjemsqrecyclebackend.repository.PickupRequestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PickupRequestService implements IPickupRequestService {

    private PickupRequestRepository pickupRequestRepository;

    public PickupRequestService(PickupRequestRepository pickupRequestRepository) {
        this.pickupRequestRepository = pickupRequestRepository;
    }

    @Override
    public List<ActivePickupRequestDTO> getActivePickupRequests() {
        List<PickupRequest> pickupRequests = pickupRequestRepository.findAllByBagsPickedUpIsNull();
        List<ActivePickupRequestDTO> activePickupRequests = new ArrayList<>();

        for (PickupRequest pickupRequest : pickupRequests) {
            ActivePickupRequestDTO activePickupRequestDTO = new ActivePickupRequestDTO();
            activePickupRequestDTO.setBagsToBeCollected(pickupRequest.getBagsToBeCollected());
            activePickupRequestDTO.setCompanyName(pickupRequest.getCompany().getCompanyName());
            activePickupRequestDTO.setCreatedAt(pickupRequest.getCreationDate());
            activePickupRequests.add(activePickupRequestDTO);
        }
        return activePickupRequests;
    }
}

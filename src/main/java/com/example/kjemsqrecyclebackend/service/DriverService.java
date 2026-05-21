package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.ActivePickupRequestDTO;
import com.example.kjemsqrecyclebackend.entity.PickupRequest;
import com.example.kjemsqrecyclebackend.repository.PickupRequestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService implements IDriverService {

    private static final String FINAL_STOP = "Retortvej 38, 2500 København";

    private final PickupRequestRepository pickupRequestRepository;

    public DriverService(PickupRequestRepository pickupRequestRepository) {
        this.pickupRequestRepository = pickupRequestRepository;
    }

    @Override
    public List<ActivePickupRequestDTO> getRouteAddresses() {
        List<PickupRequest> requests = pickupRequestRepository.findAllByBagsCollectedIsNull();
        List<ActivePickupRequestDTO> routeStops = new ArrayList<>();
        for (PickupRequest request : requests) {
            ActivePickupRequestDTO routeStop = new ActivePickupRequestDTO();
            routeStop.setCompanyName(request.getCompany().getCompanyName());
            routeStop.setAddress(request.getCompany().getAddress());
            routeStop.setBagsToBeCollected(request.getBagsToBeCollected());
            routeStop.setCreatedAt(request.getDateCreation());
            routeStops.add(routeStop);
        }
        ActivePickupRequestDTO finalStop = new ActivePickupRequestDTO();
        finalStop.setAddress(FINAL_STOP);
        routeStops.add(finalStop);

        return routeStops;
    }
}

package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.RouteStopDTO;
import com.example.kjemsqrecyclebackend.entity.PickupRequest;
import com.example.kjemsqrecyclebackend.repository.PickupRequestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DriverService implements IDriverService {

    private static final String FINAL_STOP = "Retortvej 38, 2500 København";

    private final PickupRequestRepository pickupRequestRepository;

    public DriverService(PickupRequestRepository pickupRequestRepository) {
        this.pickupRequestRepository = pickupRequestRepository;
    }

    @Override
    public List<RouteStopDTO> getRouteAddresses() {
        List<PickupRequest> requests = pickupRequestRepository.findAllByBagsCollectedIsNull();
        List<RouteStopDTO> routeStops = new ArrayList<>();
        Set<String> seenAddresses = new HashSet<>();
        for (PickupRequest request : requests) {
            String address = request.getCompany().getAddress();
            if (seenAddresses.contains(address)) {
                continue;
            }
            seenAddresses.add(address);
            RouteStopDTO routeStop = new RouteStopDTO();
            routeStop.setCompanyName(request.getCompany().getCompanyName());
            routeStop.setAddress(address);
            routeStop.setPickupRequestId(request.getId());
            routeStops.add(routeStop);
        }
        RouteStopDTO finalStop = new RouteStopDTO();
        finalStop.setAddress(FINAL_STOP);
        finalStop.setCompanyName("Qrecycle");
        routeStops.add(finalStop);

        return routeStops;
    }
}

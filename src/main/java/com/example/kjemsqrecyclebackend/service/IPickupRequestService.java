package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.ActivePickupRequestDTO;
import com.example.kjemsqrecyclebackend.entity.PickupRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPickupRequestService {

    List<ActivePickupRequestDTO> getActivePickupRequests();

}

package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.RouteStopDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDriverService {
    List<RouteStopDTO> getRouteAddresses();
}

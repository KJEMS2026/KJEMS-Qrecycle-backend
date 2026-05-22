package com.example.kjemsqrecyclebackend.controller;

import com.example.kjemsqrecyclebackend.dto.RouteStopDTO;
import com.example.kjemsqrecyclebackend.service.IDriverService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DriverController {

    private final IDriverService driverService;

    public DriverController(IDriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/driver/route")
    public List<RouteStopDTO> getRouteAddresses() {
        return driverService.getRouteAddresses();
    }
}

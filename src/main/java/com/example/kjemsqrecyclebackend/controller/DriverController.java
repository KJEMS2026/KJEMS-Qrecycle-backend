package com.example.kjemsqrecyclebackend.controller;

import com.example.kjemsqrecyclebackend.service.IDriverService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class DriverController {

    private IDriverService driverService;

    public DriverController(IDriverService driverService) {
        this.driverService = driverService;
    }
}

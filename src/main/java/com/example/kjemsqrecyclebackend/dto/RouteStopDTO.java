package com.example.kjemsqrecyclebackend.dto;

import com.example.kjemsqrecyclebackend.entity.PickupRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RouteStopDTO {
    private String companyName;
    private String address;
    private int pickupRequestId;
}

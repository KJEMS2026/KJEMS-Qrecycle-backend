package com.example.kjemsqrecyclebackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class PickupRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pickupRequestId;
    private int bagsForPickUp;
    private int bagsPickedUp;
    private LocalDateTime creationDate;
    private LocalDateTime pickUpDate;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne
    @JoinColumn(name = "picked_up_by_id")
    private User pickedUpBy;

}

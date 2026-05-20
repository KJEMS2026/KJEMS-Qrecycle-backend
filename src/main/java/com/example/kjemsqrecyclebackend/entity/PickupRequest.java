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
    private int id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "date_creation")
    private LocalDateTime creationDate;
    @Column(name = "date_collected")
    private LocalDateTime pickUpDate;

    @Column(name = "bags_to_be_collected")
    private int bagsForPickUp;

    @Column(name = "bags_collected")
    private int bagsPickedUp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User pickedUpBy;

}

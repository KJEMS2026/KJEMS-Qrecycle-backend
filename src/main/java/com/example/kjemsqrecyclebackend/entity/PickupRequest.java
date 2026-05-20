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
@Table(name = "pickup_request")
public class PickupRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "date_collected")
    private LocalDateTime dateCollected;

    @Column(name = "bags_to_be_collected")
    private Long bagsToBeCollected;

    @Column(name = "bags_collected")
    private Long bagsCollected;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

package com.example.kjemsqrecyclebackend.repository;

import com.example.kjemsqrecyclebackend.entity.PickupRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickupRequestRepository extends JpaRepository<PickupRequest, Integer> {
    List<PickupRequest> findAllByBagsToBeCollectedIsNull();
}

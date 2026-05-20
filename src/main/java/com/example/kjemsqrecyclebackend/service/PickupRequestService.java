package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.DTO.AdminPickupRequestDTO;
import com.example.kjemsqrecyclebackend.DTO.CompanyPickupRequestDTO;
import com.example.kjemsqrecyclebackend.entity.Company;
import com.example.kjemsqrecyclebackend.entity.PickupRequest;
import com.example.kjemsqrecyclebackend.entity.User;
import com.example.kjemsqrecyclebackend.repository.CompanyRepository;
import com.example.kjemsqrecyclebackend.repository.PickupRequestRepository;
import com.example.kjemsqrecyclebackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class PickupRequestService implements IPickupRequestService {

    private final PickupRequestRepository pickupRequestRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public PickupRequestService(
            PickupRequestRepository pickupRequestRepository,
            UserRepository userRepository,
            CompanyRepository companyRepository) {
        this.pickupRequestRepository = pickupRequestRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    private PickupRequest buildPickupRequest(Company company, Long bagsToBeCollected) {
        PickupRequest pickupRequest = new PickupRequest();
        pickupRequest.setCompany(company);
        pickupRequest.setBagsToBeCollected(bagsToBeCollected != null ? bagsToBeCollected : 0L);
        pickupRequest.setBagsCollected(0L);
        pickupRequest.setDateCreation(LocalDateTime.now());
        pickupRequest.setDateCollected(null);
        pickupRequest.setUser(null);
        return pickupRequest;
    }

    @Override
    public PickupRequest createForCompany(UUID authUserId, CompanyPickupRequestDTO dto) {
        User user = userRepository.findById(authUserId)
                .orElseThrow(() -> new RuntimeException("Bruger ikke fundet for auth ID: " + authUserId));

        Company company = companyRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Virksomhed ikke fundet for bruger: " + user.getId()));

        PickupRequest pickupRequest = buildPickupRequest(company, dto.getBagsToBeCollected());
        return pickupRequestRepository.save(pickupRequest);
    }

    @Override
    public PickupRequest createForAdmin(AdminPickupRequestDTO dto) {
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Virksomhed ikke fundet med ID: " + dto.getCompanyId()));

        PickupRequest pickupRequest = buildPickupRequest(company, dto.getBagsToBeCollected());
        return pickupRequestRepository.save(pickupRequest);
    }
}

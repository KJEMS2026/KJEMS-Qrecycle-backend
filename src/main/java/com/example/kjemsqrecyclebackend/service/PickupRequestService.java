package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.AdminPickupRequestDTO;
import com.example.kjemsqrecyclebackend.dto.CompanyPickupRequestDTO;
import com.example.kjemsqrecyclebackend.entity.Company;
import com.example.kjemsqrecyclebackend.entity.PickupRequest;
import com.example.kjemsqrecyclebackend.entity.User;
import com.example.kjemsqrecyclebackend.repository.CompanyRepository;
import com.example.kjemsqrecyclebackend.dto.ActivePickupRequestDTO;
import com.example.kjemsqrecyclebackend.repository.PickupRequestRepository;
import com.example.kjemsqrecyclebackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import java.util.ArrayList;
import java.util.List;

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

    private PickupRequest buildPickupRequest(Company company, int bagsToBeCollected) {
        PickupRequest pickupRequest = new PickupRequest();
        pickupRequest.setCompany(company);
        pickupRequest.setBagsToBeCollected(bagsToBeCollected);
        pickupRequest.setBagsCollected(null);
        pickupRequest.setDateCreation(LocalDateTime.now());
        pickupRequest.setDateCollected(null);
        return pickupRequest;
    }

    @Override
    public PickupRequest createForCompany(UUID authUserId, CompanyPickupRequestDTO dto) {
        User user = userRepository.findById(authUserId)
                .orElseThrow(() -> new RuntimeException("Bruger ikke fundet for auth ID: " + authUserId));

        Company company = companyRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Virksomhed ikke fundet for bruger: " + user.getId()));

        PickupRequest pickupRequest = buildPickupRequest(company, dto.getBagsToBeCollected());
        pickupRequest.setUser(null);
        return pickupRequestRepository.save(pickupRequest);
    }

    @Override
    public PickupRequest createForAdmin(AdminPickupRequestDTO dto) {
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Virksomhed ikke fundet med ID: " + dto.getCompanyId()));

        PickupRequest pickupRequest = buildPickupRequest(company, dto.getBagsToBeCollected());
        return pickupRequestRepository.save(pickupRequest);
    }

    @Override
    public List<ActivePickupRequestDTO> getActivePickupRequests() {
        List<PickupRequest> pickupRequests = pickupRequestRepository.findAllByBagsCollectedIsNull();
        List<ActivePickupRequestDTO> activePickupRequests = new ArrayList<>();

        for (PickupRequest pickupRequest : pickupRequests) {
            ActivePickupRequestDTO activePickupRequestDTO = new ActivePickupRequestDTO();
            activePickupRequestDTO.setBagsToBeCollected(pickupRequest.getBagsToBeCollected());
            activePickupRequestDTO.setCompanyName(pickupRequest.getCompany().getCompanyName());
            activePickupRequestDTO.setCreatedAt(pickupRequest.getDateCreation());
            activePickupRequests.add(activePickupRequestDTO);
        }
        return activePickupRequests;
    }

    @Override
    public List<CompanyPickupRequestDTO> getActivePickupRequestsCompany(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Bruger ikke fundet for auth ID: " + userId));

        Company company = companyRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Virksomhed ikke fundet for bruger: " + user.getId()));

        List<PickupRequest> pickupRequests = pickupRequestRepository.findAllByBagsCollectedIsNullAndCompany_Id(company.getId());
        List<CompanyPickupRequestDTO> activePickupRequestsCompany = new ArrayList<>();

        for (PickupRequest pickupRequest : pickupRequests) {
            CompanyPickupRequestDTO companyPickupRequestDTO = new CompanyPickupRequestDTO();
            companyPickupRequestDTO.setBagsToBeCollected(pickupRequest.getBagsToBeCollected());
            activePickupRequestsCompany.add(companyPickupRequestDTO);
        }

        return activePickupRequestsCompany;
    }
}

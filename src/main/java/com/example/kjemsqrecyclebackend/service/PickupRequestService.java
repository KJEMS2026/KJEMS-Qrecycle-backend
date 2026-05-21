package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.DTOMapper.PickupRequestMapper;
import com.example.kjemsqrecyclebackend.dto.AdminPickupRequestDTO;
import com.example.kjemsqrecyclebackend.dto.CompanyPickupRequestDTO;
import com.example.kjemsqrecyclebackend.dto.DriverPickupRequestDTO;
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
    private final PickupRequestMapper pickupRequestMapper;

    public PickupRequestService(
            PickupRequestRepository pickupRequestRepository,
            UserRepository userRepository,
            CompanyRepository companyRepository,
            PickupRequestMapper pickupRequestMapper) {
        this.pickupRequestRepository = pickupRequestRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.pickupRequestMapper = pickupRequestMapper;
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

    public PickupRequest updatePickupRequestAsCompleted(DriverPickupRequestDTO driverPickupRequestDTO) {
        //Possible exception occurence: What if not all a setter method in toEntity operates on a null value?
        PickupRequest pickupRequest = pickupRequestMapper.toEntity(driverPickupRequestDTO);

        try {
            return pickupRequestRepository.save(pickupRequest);
        }
        catch (IllegalArgumentException e) {
            //Note: e is attatched to the parameters of the RuntimeException, as you'd otherwise lose the
            //      what caused the new exception to exist. Usefull in debugging.
            throw new RuntimeException("Der skete en fejl i at gemme status på denne afhentning", e);
        }
    }

}
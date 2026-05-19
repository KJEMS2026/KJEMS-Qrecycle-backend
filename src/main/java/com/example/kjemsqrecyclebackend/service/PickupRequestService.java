package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.DTO.AdminPickupRequestDTO;
import com.example.kjemsqrecyclebackend.DTO.CompanyPickupRequestDTO;
import com.example.kjemsqrecyclebackend.entity.Company;
import com.example.kjemsqrecyclebackend.entity.PickupRequest;
import com.example.kjemsqrecyclebackend.entity.User;
import com.example.kjemsqrecyclebackend.repository.CompanyRepository;
import com.example.kjemsqrecyclebackend.repository.PickupRequestRepository;
import com.example.kjemsqrecyclebackend.repository.UserRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PickupRequestService implements IPickupRequestService{

    private PickupRequestRepository pickupRequestRepository;
    private UserRepository userRepository;
    private CompanyRepository companyRepository;

    public PickupRequestService(
            PickupRequestRepository pickupRequestRepository,
            UserRepository userRepository,
            CompanyRepository companyRepository) {
        this.pickupRequestRepository = pickupRequestRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    public PickupRequest createBase(Company company, Integer bags) {

        PickupRequest pickupRequest = new PickupRequest();

        pickupRequest.setCompany(company);
        pickupRequest.setBagsForPickUp(bags != null ? bags : 0);

        pickupRequest.setBagsPickedUp(0);
        pickupRequest.setCreationDate(LocalDateTime.now());

        pickupRequest.setPickUpDate(null);
        pickupRequest.setPickedUpBy(null);

        return pickupRequest;
    }

    @Override
    public PickupRequest createForCompany( CompanyPickupRequestDTO dto) {

        User user = userRepository.findBySupabaseAuthUserId(supabaseAuthUserId)
                .orElseThrow();

        Company company = companyRepository.findByUser(user)
                .orElseThrow();

        PickupRequest pickupRequest =
                createBase(company, dto.getBagsForPickUp());

        return pickupRequestRepository.save(pickupRequest);
    }

    @Override
    public PickupRequest createForAdmin(AdminPickupRequestDTO dto) {

        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow();

        PickupRequest pickupRequest = createBase(company, dto.getBagsForPickUp());

        return pickupRequestRepository.save(pickupRequest);
    }
}

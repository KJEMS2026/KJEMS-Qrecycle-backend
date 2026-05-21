package com.example.kjemsqrecyclebackend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import com.example.kjemsqrecyclebackend.DTOMapper.PickupRequestMapper;
import com.example.kjemsqrecyclebackend.dto.DriverPickupRequestDTO;
import com.example.kjemsqrecyclebackend.entity.Company;
import com.example.kjemsqrecyclebackend.entity.PickupRequest;
import com.example.kjemsqrecyclebackend.entity.User;
import com.example.kjemsqrecyclebackend.entity.UserRole;
import com.example.kjemsqrecyclebackend.service.IPickupRequestService;
import com.example.kjemsqrecyclebackend.service.PickupRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PickuRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    private PickupRequestService pickupRequestService;

    @MockitoBean
    private PickupRequestMapper pickupRequestMapper;

    private DriverPickupRequestDTO testDriverPickupRequestDTO;
    private PickupRequest testPickupRequest;
    private Company testCompany;
    private User testUser;
    private UserRole testUserRole;

    @BeforeEach
    void setupUp() {
        // user er der først, fordi company har brug for User mht. @OneToOne = nullable=false
        testUserRole = UserRole.DRIVER;

        //user DTO
        testUser = new User();
        testUser.setId(UUID.fromString("11111111-1111-1111-1111-111111111111"));
        testUser.setFirstName("Preben");
        testUser.setLastName("Daddelballe");
        testUser.setEmail("Balle.preben@test.com");
        testUser.setPhonenumber("+4512345678");
        testUser.setRole(testUserRole);

        //Company instance
        testCompany = new Company();
        testCompany.setId(1);
        testCompany.setCompanyName("Macdonalds Glostrup");
        testCompany.setAddress("Amager Strandvej 100, 2300 Copenhagen");
        testCompany.setUser(testUser);

        //Pickuprequst instance
        testPickupRequest = new PickupRequest();
        testPickupRequest.setId(100);
        testPickupRequest.setCompany(testCompany);
        testPickupRequest.setUser(testUser);
        testPickupRequest.setDateCreation(LocalDateTime.now().minusDays(2));
        testPickupRequest.setDateCollected(null);
        testPickupRequest.setBagsToBeCollected(10);
        testPickupRequest.setBagsCollected(null);

        //pickupRequstDTO instance
        testDriverPickupRequestDTO = new DriverPickupRequestDTO();
        testDriverPickupRequestDTO.setPickupRequestId(100);
        testDriverPickupRequestDTO.setBagsPickedUp(8);
        testDriverPickupRequestDTO.setBagsForPickup(10);
        testDriverPickupRequestDTO.setDifferenceInBags(
                testDriverPickupRequestDTO.getBagsForPickup() - testDriverPickupRequestDTO.getBagsPickedUp()
        );

        testDriverPickupRequestDTO.setCreationDate(LocalDateTime.now().minusDays(2));
        testDriverPickupRequestDTO.setPickupDate(LocalDateTime.now());

        testDriverPickupRequestDTO.setCompany(testCompany);
        testDriverPickupRequestDTO.setPickedUpBy(testUser);

    }
/*
    @Test
    void updatePickupRequest_ShouldReturn200_WhenUpdateSucceeds() throws Exception {

        doNothing().when(pickupRequestService)
                .updatePickupRequestAsCompleted(any(DriverPickupRequestDTO.class));

        mockMvc.perform(post("/pickup-requests/pickup/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testDriverPickupRequestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void updatePickupRequest_ShouldReturn500_WhenServiceThrowsException() throws Exception {

        doThrow(new RuntimeException())
                .when(pickupRequestService)
                .updatePickupRequestAsCompleted(any(DriverPickupRequestDTO.class));

        mockMvc.perform(post("/pickup-requests/pickup/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testDriverPickupRequestDTO)))
                .andExpect(status().isInternalServerError());
    }
    /*
}

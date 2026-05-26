package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.UserCreationDTO;
import com.example.kjemsqrecyclebackend.dto.UserDTO;
import com.example.kjemsqrecyclebackend.entity.Company;
import com.example.kjemsqrecyclebackend.entity.User;
import com.example.kjemsqrecyclebackend.repository.CompanyRepository;
import com.example.kjemsqrecyclebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService{

    private UserRepository userRepository;
    private CompanyRepository companyRepository;

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.service-key}")
    private String serviceKey;

    public UserService(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDTO> dtoUsers = new ArrayList<>();

        for(User user : users){
            UserDTO userDTO = new UserDTO();
            String fullName = user.getFirstName() + " " + user.getLastName();
            userDTO.setFullName(fullName);
            userDTO.setEmail(user.getEmail());
            userDTO.setRole(user.getRole());
            Company company = companyRepository.findByUserId(user.getId());
            userDTO.setCompany(company != null ? company.getCompanyName() : "-");
            dtoUsers.add(userDTO);
        }
        return dtoUsers;
    }

    private UUID saveAuthUser(UserCreationDTO dto) {

        try {

            String url = supabaseUrl + "/auth/v1/admin/users";

            String requestBody = String.format(
                    "{\"email\":\"%s\",\"password\":\"%s\",\"email_confirm\":true}",
                    dto.getEmail(), dto.getPassword()
            );

            HttpClient client = HttpClient.newHttpClient();
            String supaUrl = supabaseUrl + "/auth/v1/admin/users?apikey=" + serviceKey;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(supaUrl))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + serviceKey)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Supabase response: " + response.body());
            System.out.println("Env key er null: " + (System.getenv("SUPABASE_SERVICE_ROLE_KEY") == null));
            System.out.println("Spring key er null: " + (serviceKey == null || serviceKey.isEmpty()));
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response.body());
            return UUID.fromString(json.get("id").asText());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserCreationDTO saveUser(UserCreationDTO dto){
        UUID supabaseId = saveAuthUser(dto);
        User user = new User();

        user.setId(supabaseId);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhonenumber(dto.getPhonenumber());
        user.setRole(dto.getRole());
        userRepository.save(user);

        return dto;
    }

}

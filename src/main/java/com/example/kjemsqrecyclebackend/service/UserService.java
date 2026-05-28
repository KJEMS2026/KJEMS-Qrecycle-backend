package com.example.kjemsqrecyclebackend.service;

import com.example.kjemsqrecyclebackend.dto.UserCreationDTO;
import com.example.kjemsqrecyclebackend.dto.UserDTO;
import com.example.kjemsqrecyclebackend.dto.UserEditDTO;
import com.example.kjemsqrecyclebackend.entity.Company;
import com.example.kjemsqrecyclebackend.entity.User;
import com.example.kjemsqrecyclebackend.entity.UserRole;
import com.example.kjemsqrecyclebackend.repository.CompanyRepository;
import com.example.kjemsqrecyclebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private ICompanyService companyService;

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.service-key}")
    private String serviceKey;

    public UserService(UserRepository userRepository, CompanyRepository companyRepository, ICompanyService companyService) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.companyService = companyService;
    }

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDTO> dtoUsers = new ArrayList<>();

        for(User user : users){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
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

    private HttpResponse<String> sendSupabaseRequest(String url, String requestBody, String method) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + serviceKey);

            if (method.equals("POST")) {
                builder.POST(HttpRequest.BodyPublishers.ofString(requestBody));
            } else if (method.equals("PUT")) {
                builder.PUT(HttpRequest.BodyPublishers.ofString(requestBody));
            }

            return client.send(builder.build(), HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private UUID saveAuthUser(UserCreationDTO dto) {
        String url = supabaseUrl + "/auth/v1/admin/users?apikey=" + serviceKey;
        String body = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"email_confirm\":true}",
                dto.getEmail(), dto.getPassword());

        HttpResponse<String> response = sendSupabaseRequest(url, body, "POST");

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response.body());
            return UUID.fromString(json.get("id").asText());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
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

        if(dto.getRole() == UserRole.COMPANY){
            companyService.saveCompany(dto, user);
        }

        return dto;
    }

    public void deleteAuthUser(UUID userId) {

        try {
            String supaUrl = supabaseUrl + "/auth/v1/admin/users/" + userId + "?apikey=" + serviceKey;

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(supaUrl))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + serviceKey)
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to delete auth user. Status: "
                        + response.statusCode() + ", Body: " + response.body());
            }

            System.out.println("Deleted auth user: " + userId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UserEditDTO getPrefilledUserForEditForm(UUID id){
        User user = userRepository.findById(id).orElseThrow();

        UserEditDTO dto = new UserEditDTO();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhonenumber(user.getPhonenumber());

        if(user.getRole() == UserRole.COMPANY){
            companyService.getPrefilledCompanyForEditForm(id, dto);
        }

        return dto;
    }

    private void updateAuthUser(UUID id, UserEditDTO dto){
        String url = supabaseUrl + "/auth/v1/admin/users/" + id + "?apikey=" + serviceKey;
        String body = String.format("{\"email\":\"%s\",\"password\":\"%s\"}",
                dto.getEmail(), dto.getPassword());

        sendSupabaseRequest(url, body, "PUT");
    }

    @Override
    @Transactional
    public void updateUser(UUID id, UserEditDTO dto){
        User user = userRepository.findById(id).orElseThrow();

        updateAuthUser(id, dto);

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhonenumber(dto.getEmail());
        userRepository.save(user);

        if(user.getRole() == UserRole.COMPANY){
            companyService.updateCompany(dto, user);
        }
    }

}

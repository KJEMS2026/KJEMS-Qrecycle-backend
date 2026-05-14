package com.example.kjemsqrecyclebackend;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "company_name")
    private String companyName;

    private String address;

    public Company(String companyName, String address) {
        this.companyName = companyName;
        this.address = address;
    }

}

package com.example.kjemsqrecyclebackend.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expenseId;
    private String title;
    private String description;
    private String attachment;
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;
}

package com.ust.patient_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "email"),
            @UniqueConstraint(columnNames = "phone")
        },
        indexes = {
            @Index(columnList = "email"),
            @Index(columnList = "phone")
        })
public class Patient {
    @Id
    @GeneratedValue
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private LocalDate dob;
    private String address;
}

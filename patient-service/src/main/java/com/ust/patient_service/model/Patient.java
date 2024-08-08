package com.ust.patient_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Patient {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private LocalDate dob;
    private String address;
}

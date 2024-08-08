package com.ust.patient_service.dto;

import java.time.LocalDate;

public record PatientDto (
    int id,
    String fullName,
    String email,
    String phone,
    LocalDate dob,
    String address
){}

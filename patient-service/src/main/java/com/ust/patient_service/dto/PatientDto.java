package com.ust.patient_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record PatientDto (
    int id,

    @NotEmpty(message = "Name is required")
    String fullName,

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid Email")
    String email,

    @NotEmpty(message = "Phone is required")
            @Pattern(regexp = "", message = "Phone is invalid")
    String phone,

    @NotEmpty(message = "Address is required")
    String address,

    LocalDate dob
){}

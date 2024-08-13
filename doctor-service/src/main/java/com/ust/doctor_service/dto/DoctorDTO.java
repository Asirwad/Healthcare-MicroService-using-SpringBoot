package com.ust.doctor_service.dto;

import com.ust.doctor_service.domain.Doctor;
import com.ust.doctor_service.validators.ValueInList;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record DoctorDTO(
        String id,

        @NotBlank(message = "Full name is required")
        String fullName,

        @NotBlank(message = "email is required")
        @Email(message = "Invalid email")
        String email,

        @NotBlank(message = "Phone is required")
        @Pattern(regexp = "((\\+*)((0[ -]*)*|((91 )*))((\\d{12})+|(\\d{10})+))|\\d{5}([- ]*)\\d{6}")
        String phone,

        @NotBlank(message = "Addrees is required")
        String address,

        @ValueInList(allowedValues = {
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"
        },
        message = "Invalid operation day")
        String[] opDays,

        String[] specialization

){
    public static List<DoctorDTO> toDTOs(List<Doctor> doctors){
        return doctors.stream().map(DoctorDTO::fromEntity).toList();
    }
    public static Doctor toEntity(DoctorDTO doctorDTO){
        return Doctor.builder()
                .address(doctorDTO.address())
                .email(doctorDTO.email())
                .fullName(doctorDTO.fullName())
                .opDays(doctorDTO.opDays())
                .phone(doctorDTO.phone())
                .specialization(doctorDTO.specialization())
                .build();
    }

    public Doctor toDoctor(){
        return new Doctor(fullName, email, phone, address, specialization, opDays);
    }
    public static DoctorDTO fromEntity(Doctor doctor){
        return new DoctorDTO(
                doctor.getId(),
                doctor.getFullName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getAddress(),
                doctor.getOpDays(),
                doctor.getSpecialization()
        );
    }
}

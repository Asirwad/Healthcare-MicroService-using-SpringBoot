package com.ust.patient_service.repository;

import com.ust.patient_service.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface PatientRepo extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByPhone(String phone);

    Optional<Patient> findByFullNameContainingAndDob(String fullName, LocalDate dob);

    Optional<Patient> findByEmailOrPhone(String email, String phone);

    @Query("SELECT p FROM Patient p WHERE p.email LIKE %?1%")
    Optional<Patient> searchByEmail(String email);

}

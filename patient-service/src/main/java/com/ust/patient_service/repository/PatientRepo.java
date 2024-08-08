package com.ust.patient_service.repository;

import com.ust.patient_service.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Long> {

}

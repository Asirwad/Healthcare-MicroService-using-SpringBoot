package com.ust.patient_service.utils;

import com.ust.patient_service.domain.Patient;
import com.ust.patient_service.dto.PatientDTO;
import org.springframework.stereotype.Component;

@Component
public class PatientDtoConverter {
    public PatientDTO toDto(Patient patient){
        return new PatientDTO(
                patient.getId(),
                patient.getFullName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getAddress(),
                patient.getDob(),
                patient.getPreExistingIllnesses()
        );
    }

    public Patient toEntity(PatientDTO dto){
        Patient patient = new Patient();
        patient.setFullName(dto.fullName());
        patient.setEmail(dto.email());
        patient.setPhone(dto.phone());
        patient.setAddress(dto.address());
        patient.setDob(dto.dob());
        patient.setPreExistingIllnesses(dto.preExistingIllnesses());
        return patient;
    }
}

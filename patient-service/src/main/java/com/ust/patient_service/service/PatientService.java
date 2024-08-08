package com.ust.patient_service.service;

import com.ust.patient_service.domain.Patient;
import com.ust.patient_service.repository.PatientRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PatientService {
    private PatientRepo patientRepo;
    public PatientService(PatientRepo patientRepo){
        this.patientRepo = patientRepo;
    }

    public List<Patient> getAllPatients(){
        return patientRepo.findAll();
    }

    public Patient createPatient(Patient patient){
        log.debug("Creating patient : {}", patient);
        patientRepo.findByEmailOrPhone(patient.getEmail(), patient.getPhone())
                .ifPresent(p -> {
                    log.error("Patient with {} or {} Already exists!", p.getEmail(), p.getPhone());
                    throw new RuntimeException("Patient with "+p.getEmail()+" or "+p.getPhone()+"Already exists!");
                });
        log.debug("Patient {} created", patient);
        return patientRepo.save(patient);
    }

    public Patient getPatientByEmail(String email){
        log.debug("Searching patients with email id {}",email);
        return patientRepo.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Patient not found, email: "+email));
    }

    public Patient getPatient(Long id){
        log.debug("Searching patients with id {}",id);
        return patientRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Patient not found, id: "+id));
    }

    public void deletePatient(Long id){
        patientRepo.deleteById(id);
    }
}

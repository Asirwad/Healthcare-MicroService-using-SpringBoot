package com.ust.patient_service.service;

import com.ust.patient_service.domain.Patient;
import com.ust.patient_service.domain.PreExistingIllness;
import com.ust.patient_service.dto.IllnessDTO;
import com.ust.patient_service.repository.PatientRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        log.debug("Getting all patients");
        return List.copyOf(patientRepo.findAll());
    }

    public Patient createPatient(Patient patient){
        log.debug("Creating patient : {}", patient);
        patientRepo.findByEmailOrPhone(patient.getEmail(), patient.getPhone())
                .ifPresent(p -> {
                    log.error("Patient with {} or {} Already exists!", p.getEmail(), p.getPhone());
                    throw new RuntimeException("Patient with "+p.getEmail()+" or "+p.getPhone()+"Already exists!");
                });
        log.debug("Patient does not exist with email: {} or phone: {} created", patient.getEmail(), patient.getPhone());
        return patientRepo.save(patient);
    }

    public Patient getPatientByEmail(String email){
        log.debug("Searching patients with email id {}",email);
        return patientRepo.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Patient not found, email: "+email));
    }

    public Patient getPatient(long id){
        log.debug("Searching patients with id {}",id);
        return patientRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Patient not found, id: "+id));
    }

    public void deletePatient(Long id){
        log.debug("Updating patient, id: {}", id);
        patientRepo.deleteById(id);
    }

    public Patient addIllnessToPatient(Long id, PreExistingIllness illness){
        log.debug("Adding an illness to patient with id: {}",id);
        Patient patientOld = patientRepo.findById(id).orElseThrow();
        patientOld.getPreExistingIllnesses().add(illness);
        return patientRepo.save(patientOld);
    }

    public Patient updatePatient(Patient patient){
        log.debug("Updating patient: {}", patient);
        return patientRepo.save(patient); // to be replaced by saveAndFlush
    }

    public Optional<Patient> searchByEmail(String email){
        log.debug("Searching patient by email : {}", email);
        return patientRepo.searchByEmail(email);
    }

    public Patient searchByPhone(String phone){
        log.debug("Searching patient by phone: {}", phone);
        return patientRepo.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Patient not found!, phone: "+ phone));
    }
}

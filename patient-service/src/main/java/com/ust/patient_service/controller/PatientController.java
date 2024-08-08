package com.ust.patient_service.controller;

import com.ust.patient_service.domain.Patient;
import com.ust.patient_service.dto.PatientDto;
import com.ust.patient_service.repository.PatientRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    PatientRepo patientRepo;

    @PostMapping("/create")
    public ResponseEntity<Patient> createPatient2(@RequestBody PatientDto dto){
       try{
           Patient patient = new Patient();
           patient.setFullName(dto.fullName());
           patient.setEmail(dto.email());
           patient.setPhone(dto.phone());
           patient.setAddress(dto.address());
           patient.setDob(dto.dob());
           patientRepo.save(patient);
           return new ResponseEntity<>(patient, HttpStatus.CREATED);
       }catch (Exception ex){
           System.out.println(ex);
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable("id") long id){
        try{
            patientRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

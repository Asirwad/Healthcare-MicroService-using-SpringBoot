package com.ust.patient_service.controller;

import com.ust.patient_service.domain.Patient;
import com.ust.patient_service.dto.PatientDto;
import com.ust.patient_service.repository.PatientRepo;
import com.ust.patient_service.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/")
    public ResponseEntity<List<Patient>> findAllPatient(){
        return ResponseEntity.ok().body(patientService.getAllPatients());
    }

    @PostMapping("/create")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient){
       try{
           patientService.createPatient(patient);
           return new ResponseEntity<>(patient, HttpStatus.CREATED);
       }catch (Exception ex){
           System.out.println(ex);
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable("id") long id){
        try{
            patientService.deletePatient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable("id") long id){
        try{
            return ResponseEntity.ok(patientService.getPatient(id));
        }catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/find/{email}")
    public ResponseEntity<Patient> getPatientByEmail(@PathVariable String email){
        try {
            return new ResponseEntity<>(patientService.getPatientByEmail(email), HttpStatus.ACCEPTED);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

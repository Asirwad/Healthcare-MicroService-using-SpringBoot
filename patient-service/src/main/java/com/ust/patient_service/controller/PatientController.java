package com.ust.patient_service.controller;

import com.ust.patient_service.domain.Patient;
import com.ust.patient_service.domain.PreExistingIllness;
import com.ust.patient_service.dto.IllnessDTO;
import com.ust.patient_service.dto.PatientDTO;
import com.ust.patient_service.repository.PatientRepo;
import com.ust.patient_service.service.PatientService;
import com.ust.patient_service.utils.PatientDtoConverter;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/patients")
@Slf4j
public class PatientController {

    private final PatientService patientService;
    private final PatientDtoConverter patientDtoConverter;

    public PatientController(PatientService patientService, PatientDtoConverter patientDtoConverter){
        this.patientService = patientService;
        this.patientDtoConverter = patientDtoConverter;
    }

    @GetMapping("/")
    public ResponseEntity<List<Patient>> findAllPatient(){
        return ResponseEntity.ok().body(patientService.getAllPatients());
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody PatientDTO dto){
        // TODO handle DuplicatePatientException
        Patient patient = patientDtoConverter.toEntity(dto);
        patient = patientService.createPatient(patient);
        var responseBody = patientDtoConverter.toDto(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PostMapping("/{id}/illness")
    public ResponseEntity<IllnessDTO> addNewIllnessToPatient(@Valid @RequestBody IllnessDTO dto, @PathVariable long id){
        PreExistingIllness illness = new PreExistingIllness();
        illness.setIllnessName(dto.illnessName());
        patientService.addIllnessToPatient(id, illness);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable("id") long id){
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable("id") long id){
        //
        return ResponseEntity.ok(patientService.getPatient(id));
    }

    @GetMapping
    public ResponseEntity<PatientDTO> searchForPatient(
            @RequestParam(name = "k") String key,
            @RequestParam(name = "v") String data
    ){
        Patient patient;
        if(key.equals("email")){
            patient = patientService.getPatientByEmail(data);
        } else {
            patient = patientService.searchByPhone(data);
        }
        return ResponseEntity.ok().body(patientDtoConverter.toDto(patient));
    }


}

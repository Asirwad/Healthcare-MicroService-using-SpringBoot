package com.ust.patient_service.controller;

import com.ust.patient_service.domain.Patient;
import com.ust.patient_service.domain.PreExistingIllness;
import com.ust.patient_service.dto.IllnessDTO;
import com.ust.patient_service.dto.PatientDTO;
import com.ust.patient_service.repository.PatientRepo;
import com.ust.patient_service.service.PatientService;
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

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    // handle runtime exception
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e){
        log.error("An error occurred: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    // handle Method Argument Not Valid Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e){
        log.error("An error occurred: ", e);
        var fieldErrors = e.getFieldErrors();
        Map<Object,Object> errors = new HashMap<>();
        fieldErrors.forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    private PatientDTO toDto(Patient patient){
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

    private Patient toEntity(PatientDTO dto){
        Patient patient = new Patient();
        patient.setFullName(dto.fullName());
        patient.setEmail(dto.email());
        patient.setPhone(dto.phone());
        patient.setAddress(dto.address());
        patient.setDob(dto.dob());
        patient.setPreExistingIllnesses(dto.preExistingIllnesses());
        return patient;
    }

    @GetMapping("/")
    public ResponseEntity<List<Patient>> findAllPatient(){
        return ResponseEntity.ok().body(patientService.getAllPatients());
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody PatientDTO dto){
        Patient patient = toEntity(dto);
        patient = patientService.createPatient(patient);
        dto = toDto(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PostMapping("/{id}/illness")
    public ResponseEntity<IllnessDTO> addNewIllnessToPatient(@Valid @RequestBody IllnessDTO dto, @PathVariable long id){
        PreExistingIllness illness = new PreExistingIllness();
        illness.setIllnessName(dto.illnessName());
        patientService.addIllnessToPatient(id, illness);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable("id") long id){
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable("id") long id){
        return ResponseEntity.ok(patientService.getPatient(id));
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<Patient> getPatientByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok().body(patientService.getPatientByEmail(email));
    }


}

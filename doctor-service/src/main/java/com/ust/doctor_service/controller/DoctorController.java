package com.ust.doctor_service.controller;

import com.ust.doctor_service.dto.DoctorDTO;
import com.ust.doctor_service.service.DoctorService;
import com.ust.doctor_service.service.DoctorServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorServiceImpl doctorService;

    public DoctorController(DoctorServiceImpl doctorService){
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@Valid @RequestBody DoctorDTO dto){
        var doctor = doctorService.createDoctor(dto.toDoctor());
        return ResponseEntity.status(HttpStatus.CREATED).body(DoctorDTO.fromEntity(doctor));
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors(){
        var doctors = doctorService.getAllDoctors();
        if(doctors.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(DoctorDTO.toDTOs(doctors));
    }
}

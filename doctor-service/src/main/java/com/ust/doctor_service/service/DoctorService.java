package com.ust.doctor_service.service;

import com.ust.doctor_service.domain.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DoctorService {
    Doctor createDoctor(Doctor doctor);

    boolean checkIfDoctorExists(String doctorId);

    List<Doctor> getDoctorsByOpDays(String[] days);

    List<Doctor> getAllDoctors();
}

package com.ust.doctor_service.service;

import com.ust.doctor_service.domain.Doctor;
import com.ust.doctor_service.repository.DoctorRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepo doctorRepo;

    public DoctorServiceImpl(DoctorRepo doctorRepo){
        this.doctorRepo = doctorRepo;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepo.save(doctor);
    }

    @Override
    public boolean checkIfDoctorExists(String doctorId) {
        return doctorRepo.existsById(doctorId);
    }

    @Override
    public List<Doctor> getDoctorsByOpDays(String[] days) {
        return List.copyOf(doctorRepo.findAllByOpDaysContains(days));
    }

    @Override
    public List<Doctor> getAllDoctors(){
        return List.copyOf(doctorRepo.findAll());
    }
}

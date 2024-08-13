package com.ust.doctor_service.repository;

import com.ust.doctor_service.domain.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DoctorRepo extends MongoRepository <Doctor, String>{
    List<Doctor> findAllByOpDaysContains(String[] days);
}

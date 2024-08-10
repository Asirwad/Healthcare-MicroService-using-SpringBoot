package com.ust.patient_service.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity @EntityListeners(AuditingEntityListener.class)
public class PreExistingIllness {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String illnessName;
}

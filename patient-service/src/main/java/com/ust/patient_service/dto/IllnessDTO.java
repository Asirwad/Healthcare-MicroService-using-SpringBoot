package com.ust.patient_service.dto;

import jakarta.validation.constraints.NotEmpty;

public record IllnessDTO(
      int id,
      @NotEmpty(message = "Name is required")
      String illnessName
){}

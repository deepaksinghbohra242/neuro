package com.neuromed.pharmarcy.service.client;

import com.neuromed.pharmarcy.dto.PatientDetailsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PatientFallback implements PatientFeignClient{
    @Override
    public ResponseEntity<PatientDetailsDTO> fetchPatientDetails(String correlationId, Long patientId) {
        return ResponseEntity.ok(null);
    }
}

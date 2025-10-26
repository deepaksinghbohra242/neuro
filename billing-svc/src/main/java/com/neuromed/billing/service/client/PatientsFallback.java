package com.neuromed.billing.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PatientsFallback implements PatientsFeignClient {
    @Override
    public ResponseEntity<com.neuromed.billing.dto.PatientDTO> fetchPatientDetails(String correlationId, String patientId) {
        return null;
    }
}

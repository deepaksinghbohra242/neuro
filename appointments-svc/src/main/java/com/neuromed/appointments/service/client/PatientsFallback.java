package com.neuromed.appointments.service.client;

import com.neuromed.appointments.dto.PatientDTO;
import com.neuromed.appointments.dto.PatientDetailsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PatientsFallback implements PatientsFeignClient {
    @Override
    public ResponseEntity<PatientDetailsDTO> fetchPatientDetails(String correlationId, Long patientId) {
        return null;
    }
}

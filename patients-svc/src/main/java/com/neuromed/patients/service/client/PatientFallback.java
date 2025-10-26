package com.neuromed.patients.service.client;

import com.neuromed.patients.dto.*;
import org.springframework.stereotype.Component;

@Component
public class PatientFallback implements PatientFeignClient {

    @Override
    public PatientDTO getPatientById(Long patientId) {
        return new PatientDTO(); // empty fallback
    }

    @Override
    public MedicalDetailsDTO getMedicalDetails(Long patientId) {
        return new MedicalDetailsDTO();
    }

    @Override
    public GpDetailsDTO getGpDetails(Long patientId) {
        return new GpDetailsDTO();
    }

}

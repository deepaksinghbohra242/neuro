package com.neuromed.patients.service.client;

import com.neuromed.patients.dto.GpDetailsDTO;
import com.neuromed.patients.dto.MedicalDetailsDTO;
import com.neuromed.patients.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patients-svc", fallback = PatientFallback.class)
public interface PatientFeignClient {

    @GetMapping("/api/patients/{patientId}")
    PatientDTO getPatientById(@PathVariable("patientId") Long patientId);

    @GetMapping("/api/medical-details/{patientId}")
    MedicalDetailsDTO getMedicalDetails(@PathVariable("patientId") Long patientId);

    @GetMapping("/api/gp-details/{patientId}")
    GpDetailsDTO getGpDetails(@PathVariable("patientId") Long patientId);
}

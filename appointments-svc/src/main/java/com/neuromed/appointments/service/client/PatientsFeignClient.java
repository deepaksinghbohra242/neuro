package com.neuromed.appointments.service.client;

import com.neuromed.appointments.dto.PatientDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="patients",fallback = PatientsFallback.class)
public interface PatientsFeignClient {

    @GetMapping(value = "/api/fetch",consumes = "application/json")
    public ResponseEntity<PatientDetailsDTO> fetchPatientDetails(@RequestHeader("neuromed-correlation-id")
                                                     String correlationId, @RequestParam("patientId") Long patientId);

}

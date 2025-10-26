package com.neuromed.billing.service.client;

import com.neuromed.billing.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="patients",fallback = PatientsFallback.class)
public interface PatientsFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<PatientDTO> fetchPatientDetails(@RequestHeader("neuromed-correlation-id")
                                                          String correlationId, @RequestParam("patientId") String patientId);

}

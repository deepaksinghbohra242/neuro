package com.neuromed.pharmarcy.service.client;

import com.neuromed.pharmarcy.dto.PatientDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="patients",fallback = PatientFallback.class)
public interface PatientFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    ResponseEntity<PatientDetailsDTO> fetchPatientDetails(
            @RequestHeader("neuromed-correlation-id") String correlationId,
            @RequestParam("patientId") Long patientId);
}

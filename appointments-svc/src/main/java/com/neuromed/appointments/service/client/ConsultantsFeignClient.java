package com.neuromed.appointments.service.client;

import com.neuromed.appointments.dto.ConsultantDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "consultants", fallback = ConsultantsFallback.class)
public interface ConsultantsFeignClient {

    @GetMapping(value = "/api/fetchConsultantDetails", consumes = "application/json")
    ResponseEntity<ConsultantDetailsDto> fetchConsultantDetails(
            @RequestHeader("neuromed-correlation-id") String correlationId,
            @RequestParam("consultantId") Long consultantId);
}

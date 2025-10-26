package com.neuromed.pharmarcy.service.client;


import com.neuromed.pharmarcy.dto.ConsultantDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ConsultantsFallback implements ConsultantsFeignClient {
    @Override
    public ResponseEntity<ConsultantDetailsDto> fetchConsultantDetails(String correlationId, Long consultantId) {
        return ResponseEntity.ok(null);
    }
}
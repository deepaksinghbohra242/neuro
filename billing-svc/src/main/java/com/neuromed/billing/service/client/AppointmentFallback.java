package com.neuromed.billing.service.client;

import com.neuromed.billing.dto.AppointmentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AppointmentFallback implements AppointmentFeignClient{
    @Override
    public ResponseEntity<AppointmentDTO> fetchAppointmentDetails(String correlationId, String appointmentId) {
        return null;
    }
}

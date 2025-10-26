package com.neuromed.patients.service.client;

import com.neuromed.patients.dto.AppointmentOtherDetailsDTO;
import org.springframework.stereotype.Component;

@Component
public class AppointmentFallback implements AppointmentFeignClient {
    @Override
    public AppointmentOtherDetailsDTO getAppointmentDetails(Long id) {
        return AppointmentOtherDetailsDTO.builder()
                .id(id)
                .fullName("Service Unavailable: fallback")
                .build();
    }
}


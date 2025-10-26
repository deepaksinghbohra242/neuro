package com.neuromed.patients.service.client;

import com.neuromed.patients.dto.AppointmentOtherDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "appointments-svc", fallback = AppointmentFallback.class)
public interface AppointmentFeignClient {

    @GetMapping("/api/appointment-other-details/{id}")
    AppointmentOtherDetailsDTO getAppointmentDetails(@PathVariable("id") Long id);
}

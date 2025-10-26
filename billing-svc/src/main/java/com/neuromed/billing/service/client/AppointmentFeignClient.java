package com.neuromed.billing.service.client;

import com.neuromed.billing.dto.AppointmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="appointments",fallback = AppointmentFallback.class)
public interface AppointmentFeignClient {

    @GetMapping(value = "/appointments/fetch", consumes = "application/json")
    public ResponseEntity<AppointmentDTO> fetchAppointmentDetails(@RequestHeader("neuromed-correlation-id")
                                                          String correlationId, @RequestParam("appointmentId") String appointmentId);

}

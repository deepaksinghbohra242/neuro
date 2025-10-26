package com.neuromed.consultants.service.client;

import com.neuromed.consultants.dto.AppointmentResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "appointments", url = "http://localhost:8083/api", fallback = AppointmentClientFallback.class)
public interface AppointmentClient {

        @GetMapping("/byVisitTypeAndDate")
        List<AppointmentResponseDto> getAppointmentsByVisitTypeAndDate(
                        @RequestParam("visitType") String visitType,
                        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);
}

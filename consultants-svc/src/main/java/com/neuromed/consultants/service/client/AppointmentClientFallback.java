package com.neuromed.consultants.service.client;

import com.neuromed.consultants.dto.AppointmentResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Component
public class AppointmentClientFallback implements AppointmentClient {

  @Override
  public List<AppointmentResponseDto> getAppointmentsByVisitTypeAndDate(String visitType, LocalDate date) {
    return Collections.emptyList();
  }
}

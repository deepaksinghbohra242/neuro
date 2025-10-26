package com.neuromed.consultants.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentResponseDto {
  private Long id;
  private Long patientId;
  private Long consultantId;
  private LocalDate date;
  private LocalTime timeSlot;
  private String status;
  private String visitType;
  private String reason;
  private Long billingId;
}

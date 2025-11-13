package com.neuromed.consultants.dto;

import lombok.Data;

@Data
public class ScheduleDTO {
  private String appointmentId;
  private String testDocumentId;
  private Integer consultationsCount;
}

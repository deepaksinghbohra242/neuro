package com.neuromed.consultants.dto;

import lombok.Data;

@Data
public class ReorderRequestDTO {
  private Long prescriptionId;
  private Long patientId;
  private Long consultantId;
}

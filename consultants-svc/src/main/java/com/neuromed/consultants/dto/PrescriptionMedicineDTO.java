package com.neuromed.consultants.dto;

import lombok.Data;

@Data
public class PrescriptionMedicineDTO {
  private Long id;
  private Long prescriptionId;
  private String medicineName;
  private String prescribedDose;
  private String frequency;
  private Integer noOfOrder;
}

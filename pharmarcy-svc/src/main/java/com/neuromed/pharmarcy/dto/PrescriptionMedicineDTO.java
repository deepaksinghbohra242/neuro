package com.neuromed.pharmarcy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "PrescriptionMedicine", description = "Schema to hold Prescription medicine information")
public class PrescriptionMedicineDTO {

  private Long id;
  private Long prescriptionId;
  private String medicineName;
  private String prescribedDose;
  private String frequency;
  private Integer noOfOrder;
}

package com.neuromed.pharmarcy.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDTO {
  private Long id;
  private Long patientId;
  private Long consultantId;
  private Long pharmacyId;
  private Timestamp date;
  private Integer noOfMedicines;
  private Integer duration;
  private String preferredService;
  private UserModel consultantUserModel ;
}

package com.neuromed.consultants.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
  private Long id;
  private Long patientId;
  private Long prescriptionId;
  private String status; // return / reject / approve
}

package com.neuromed.pharmarcy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionMedicine extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "prescription_id", nullable = false)
  private Long prescriptionId;

  @Column(name = "medicine_name", nullable = false)
  private String medicineName;

  @Column(name = "prescribed_dose", nullable = false)
  private String prescribedDose;

  @Column(name = "frequency", nullable = false)
  private String frequency;

  @Column(name = "no_of_order", nullable = false)
  private Integer noOfOrder;
}

package com.neuromed.patients.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "treatments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Treatment extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "treatment_name", nullable = false)
  private String treatmentName;

  @Column(name = "link")
  private String link;
}

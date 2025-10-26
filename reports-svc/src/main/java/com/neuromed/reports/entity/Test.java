package com.neuromed.reports.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Test extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private LocalDateTime date;

  @Column(name = "prescription_id")
  private Integer prescriptionId;

  @Column(name = "consultant_id")
  private Integer consultantId;

  @Column(name = "appointment_id")
  private Long appointmentId;

  @Builder.Default
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status status = Status.PENDING;

  @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Document> documents;

  public enum Status {
    PENDING,
    COMPLETED
  }
}

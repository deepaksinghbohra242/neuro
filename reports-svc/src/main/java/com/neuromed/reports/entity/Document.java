package com.neuromed.reports.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Document extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private LocalDate date;

  private String url;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "test_id", nullable = false)
  private Test test;
}

package com.neuromed.consultants.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TestDTO {
  private Integer id;
  private String name;
  private LocalDateTime date;
  private Integer prescriptionId;
  private Integer consultantId;
  private Long appointmentId;
  // private Test.Status status;
  // private List<DocumentDTO> documents;
}

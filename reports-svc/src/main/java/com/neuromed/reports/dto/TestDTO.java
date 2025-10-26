package com.neuromed.reports.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import com.neuromed.reports.entity.Test;

@Data
public class TestDTO {
  private Integer id;
  private String name;
  private LocalDateTime date;
  private Integer prescriptionId;
  private Integer consultantId;
  private Long appointmentId;
  private Test.Status status;
  private List<DocumentDTO> documents;
}

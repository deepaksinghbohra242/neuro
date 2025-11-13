package com.neuromed.consultants.dto;

import lombok.Data;

@Data
public class TestDocumentsSummaryDTO {
  private Integer id;
  private String testId;
  private String name;
  private Integer totalCount;
}

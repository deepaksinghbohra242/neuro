package com.neuromed.consultants.dto;

import lombok.Data;

@Data
public class TestListItemDTO {
  private Integer id;
  private String testId;
  private String date;
  private String assignedBy;
  private String name;
  private String attachment;
}

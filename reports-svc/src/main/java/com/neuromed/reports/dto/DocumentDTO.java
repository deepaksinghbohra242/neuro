package com.neuromed.reports.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DocumentDTO {
  private Integer id;
  private String name;
  private LocalDate date;
  private String url;
}

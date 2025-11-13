package com.neuromed.consultants.dto;

import lombok.Data;
import java.util.List;

@Data
public class TestDocumentDetailResponseDTO {
  private ScheduleDTO schedule;
  private OverviewDTO overview;
  private TestDTO totalTests;
}

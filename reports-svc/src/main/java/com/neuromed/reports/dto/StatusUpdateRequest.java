package com.neuromed.reports.dto;

import com.neuromed.reports.entity.Test;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusUpdateRequest {
  private Test.Status status;
}

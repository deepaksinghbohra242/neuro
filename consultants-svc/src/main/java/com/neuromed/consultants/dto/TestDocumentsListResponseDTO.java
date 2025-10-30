package com.neuromed.consultants.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TestDocumentsListResponseDTO {
  private List<TestDocumentsSummaryDTO> testsDocuments;
}

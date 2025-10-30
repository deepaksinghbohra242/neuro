package com.neuromed.consultants.service.client;

import com.neuromed.consultants.dto.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class TestDocumentsClientFallback implements TestDocumentsClient {

  @Override
  public List<TestDocumentsSummaryDTO> getAllTestsDocuments(Long patientId, String correlationId) {
    log.error("Fallback triggered for getAllTestsDocuments. PatientId: {}, CorrelationId: {}", patientId,
        correlationId);
    return null;
  }

  @Override
  public TestDocumentDetailResponseDTO getTestDocumentDetails(Long patientId, String testDocumentId,
      String correlationId) {
    log.error("Fallback triggered for getTestDocumentDetails. TestDocumentId: {}, CorrelationId: {}", testDocumentId,
        correlationId);
    return null;
  }

  @Override
  public TestDocumentsListResponseDTO uploadAttachment(Long testId, MultipartFile file, String description,
      String correlationId) {
    return null;
  }

}

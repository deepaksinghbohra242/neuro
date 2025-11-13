package com.neuromed.consultants.controller;

import com.neuromed.consultants.dto.*;
import com.neuromed.consultants.service.client.TestDocumentsClient;
import com.neuromed.consultants.service.client.TestClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Consultant Test Management", description = "APIs for Consultants to manage patient tests and documents")
@RestController
@RequestMapping("/api/con_patients")
@RequiredArgsConstructor
public class PatientTestController {

  private final TestDocumentsClient testDocumentsClient;
  private final TestClient testClient;

  // 1️⃣ Get list of all test documents for a patient
  @Operation(summary = "Get all test documents for a patient")
  @GetMapping("/{patientId}/tests/documents")
  public ResponseEntity<TestDocumentsListResponseDTO> getAllTestDocuments(
      @PathVariable Long patientId,
      @RequestHeader("neuromed-correlation-id") String correlationId) {

    List<TestDocumentsSummaryDTO> documents = testDocumentsClient.getAllTestsDocuments(patientId, correlationId);
    return ResponseEntity.ok(new TestDocumentsListResponseDTO(documents));
  }

  // 2️⃣ Get detailed document info for a particular test
  @Operation(summary = "Get detailed test document info")
  @GetMapping("/{patientId}/tests/documents/{testDocumentId}")
  public ResponseEntity<TestDocumentDetailResponseDTO> getTestDocumentDetail(
      @PathVariable Long patientId,
      @PathVariable String testDocumentId,
      @RequestHeader("neuromed-correlation-id") String correlationId) {

    TestDocumentDetailResponseDTO detail = testDocumentsClient.getTestDocumentDetails(patientId, testDocumentId,
        correlationId);
    return ResponseEntity.ok(detail);
  }

  // 3️⃣ Assign a new test to a patient
  @Operation(summary = "Assign a new test to a patient")
  @PostMapping(value = "/{patientId}/tests", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TestListItemDTO> assignNewTest(
      @PathVariable Long patientId,
      @RequestBody TestListItemDTO testDTO,
      @RequestHeader("neuromed-correlation-id") String correlationId) {

    TestListItemDTO createdTest = testClient.createTest(patientId, testDTO, correlationId);
    return ResponseEntity.ok(createdTest);
  }

  // 4️⃣ Upload document for a test
  @Operation(summary = "Upload a document for a test")
  @PostMapping(value = "/{patientId}/tests/{testId}/attachments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<TestDocumentsListResponseDTO> uploadDocument(
      @PathVariable Long patientId,
      @PathVariable Long testId,
      @RequestPart("file") MultipartFile file,
      @RequestParam(value = "description", required = false) String description,
      @RequestHeader("neuromed-correlation-id") String correlationId) {

    TestDocumentsListResponseDTO uploadedDocument = testDocumentsClient.uploadAttachment(testId, file, description,
        correlationId);
    return ResponseEntity.ok(uploadedDocument);
  }
}

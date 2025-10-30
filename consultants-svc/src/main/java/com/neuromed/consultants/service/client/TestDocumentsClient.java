package com.neuromed.consultants.service.client;

import com.neuromed.consultants.dto.TestDocumentsSummaryDTO;
import com.neuromed.consultants.dto.TestDocumentDetailResponseDTO;
import com.neuromed.consultants.dto.TestDocumentsListResponseDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "reports", contextId = "testDocumentsClient", fallback = TestDocumentsClientFallback.class)
public interface TestDocumentsClient {

        @GetMapping("/patients/{patientId}")
        List<TestDocumentsSummaryDTO> getAllTestsDocuments(@PathVariable("patientId") Long patientId,
                        @RequestHeader("neuromed-correlation-id") String correlationId);

        @GetMapping("/patients/{patientId}/{testDocumentId}")
        TestDocumentDetailResponseDTO getTestDocumentDetails(@PathVariable("patientId") Long patientId,
                        @PathVariable("testDocumentId") String testDocumentId,
                        @RequestHeader("neuromed-correlation-id") String correlationId);

        @PostMapping(value = "/api/tests/{testId}/attachments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        TestDocumentsListResponseDTO uploadAttachment(
                        @PathVariable("testId") Long testId,
                        @RequestPart("file") MultipartFile file,
                        @RequestParam(value = "description", required = false) String description,
                        @RequestHeader("neuromed-correlation-id") String correlationId);
}

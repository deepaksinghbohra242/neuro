package com.neuromed.reports.controller;

import com.neuromed.reports.constant.TestConstants;
import com.neuromed.reports.dto.DocumentDTO;
import com.neuromed.reports.dto.TestDTO;
import com.neuromed.reports.dto.ErrorResponseDto;
import com.neuromed.reports.dto.ResponseDto;
import com.neuromed.reports.service.TestService;
import com.neuromed.reports.dto.StatusUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Tag(name = "CRUD REST APIs for Tests", description = "APIs to CREATE, UPDATE, FETCH, and DELETE Test details and related documents")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

  private final TestService testService;

  @Operation(summary = "Create Test", description = "Create a new Test record")
  @ApiResponses({
      @ApiResponse(responseCode = TestConstants.STATUS_201, description = TestConstants.MESSAGE_201),
      @ApiResponse(responseCode = TestConstants.STATUS_500, description = TestConstants.MESSAGE_500)
  })
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TestDTO> createTest(@RequestBody TestDTO dto) {
    TestDTO createdTest = testService.createTest(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdTest);
  }

  @Operation(summary = "Fetch All Tests", description = "Retrieve all Test records")
  @ApiResponses({
      @ApiResponse(responseCode = TestConstants.STATUS_200, description = TestConstants.MESSAGE_200),
      @ApiResponse(responseCode = TestConstants.STATUS_500, description = TestConstants.MESSAGE_500)
  })
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TestDTO>> getAllTests() {
    List<TestDTO> tests = testService.getAllTests();
    return ResponseEntity.ok(tests);
  }

  @Operation(summary = "Upload Attachment", description = "Attach a document to a Test")
  @ApiResponses({
      @ApiResponse(responseCode = TestConstants.STATUS_200, description = TestConstants.ATTACHMENT_UPLOAD_SUCCESS),
      @ApiResponse(responseCode = TestConstants.STATUS_500, description = TestConstants.MESSAGE_500)
  })
  @PostMapping(value = "/{testId}/attachments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<DocumentDTO> uploadAttachment(
      @PathVariable Integer testId,
      @RequestParam("file") MultipartFile file,
      @RequestParam(value = "description", required = false) String description) {

    DocumentDTO uploadedDoc = testService.uploadAttachment(testId, file, description);
    return ResponseEntity.status(HttpStatus.OK).body(uploadedDoc);
  }

  @Operation(summary = "Delete Attachment", description = "Delete a Test document by document ID")
  @ApiResponses({
      @ApiResponse(responseCode = TestConstants.STATUS_200, description = TestConstants.ATTACHMENT_DELETED_SUCCESS),
      @ApiResponse(responseCode = TestConstants.STATUS_417, description = TestConstants.ATTACHMENT_NOT_FOUND),
      @ApiResponse(responseCode = TestConstants.STATUS_500, description = TestConstants.MESSAGE_500)
  })
  @DeleteMapping("/attachments/{documentId}")
  public ResponseEntity<ResponseDto> deleteAttachment(@PathVariable Integer documentId) {
    try {
      testService.deleteAttachment(documentId);
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(TestConstants.STATUS_200, TestConstants.ATTACHMENT_DELETED_SUCCESS));
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDto(TestConstants.STATUS_417, TestConstants.ATTACHMENT_NOT_FOUND));
    }
  }

  @Operation(summary = "Replace Attachment", description = "Replace existing document details or file")
  @ApiResponses({
      @ApiResponse(responseCode = TestConstants.STATUS_200, description = TestConstants.ATTACHMENT_REPLACED_SUCCESS),
      @ApiResponse(responseCode = TestConstants.STATUS_417, description = TestConstants.ATTACHMENT_NOT_FOUND),
      @ApiResponse(responseCode = TestConstants.STATUS_500, description = TestConstants.MESSAGE_500, content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @PutMapping(value = "/attachments/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<DocumentDTO> replaceAttachment(
      @PathVariable Integer id,
      @RequestParam("name") String name,
      @RequestParam("date") String dateStr,
      @RequestParam(value = "file", required = false) MultipartFile file) {

    LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);

    DocumentDTO dto = new DocumentDTO();
    dto.setName(name);
    dto.setDate(date);

    DocumentDTO updatedDoc = testService.replaceAttachment(id, dto, file);
    if (updatedDoc != null) {
      return ResponseEntity.ok(updatedDoc);
    } else {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }
  }

  @Operation(summary = "Update Test Status", description = "Update the status of a Test")
  @ApiResponses({
      @ApiResponse(responseCode = TestConstants.STATUS_200, description = "Status updated successfully"),
      @ApiResponse(responseCode = TestConstants.STATUS_404, description = TestConstants.MESSAGE_417_UPDATE_TEST),
      @ApiResponse(responseCode = TestConstants.STATUS_500, description = TestConstants.MESSAGE_500)
  })
  @PatchMapping(value = "/{testId}/status", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseDto> updateTestStatus(
      @PathVariable Integer testId,
      @RequestBody StatusUpdateRequest statusUpdateRequest) {
    try {
      testService.updateStatus(testId, statusUpdateRequest.getStatus());
      return ResponseEntity.ok(new ResponseDto(TestConstants.STATUS_200, TestConstants.MESSAGE_417_UPDATE_STATUS));
    } catch (IllegalArgumentException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ResponseDto(
              TestConstants.STATUS_404,
              TestConstants.MESSAGE_417_UPDATE_TEST));
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDto(
              TestConstants.STATUS_500,
              TestConstants.MESSAGE_500));
    }
  }

  @Operation(summary = "Get Tests by Appointment ID", description = "Retrieve all Test records for a given appointment")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Tests retrieved successfully"),
          @ApiResponse(responseCode = "404", description = "No tests found for the appointment"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  @GetMapping("/test/byAppointment")
  public ResponseEntity<List<TestDTO>> getTestsByAppointment(@RequestParam("appointmentId") Long appointmentId) {
    List<TestDTO> tests = testService.getTestsByAppointmentId(appointmentId);
    if (tests.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tests);
    }
    return ResponseEntity.ok(tests);
  }

}

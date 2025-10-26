package com.neuromed.reports.controller;
import com.neuromed.reports.constants.ReportsConstants;
import com.neuromed.reports.dto.ErrorResponseDto;
import com.neuromed.reports.dto.ResponseDto;
import com.neuromed.reports.dto.TestDocumentsDTO;
import com.neuromed.reports.service.ITestDocumentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Test Documents Management",
        description = "CRUD REST APIs for managing test documents"
)
@RestController
@RequestMapping("/api/test-documents")
@RequiredArgsConstructor
public class TestDocumentsController {

    private final ITestDocumentsService testDocumentsService;

    @Operation(summary = "Create a new test document", description = "Adds a new document to a specific test")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Document created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<ResponseDto> createTestDocument(@RequestBody TestDocumentsDTO testDocumentsDTO) {
        testDocumentsService.createTestDocument(testDocumentsDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ReportsConstants.STATUS_201, ReportsConstants.MESSAGE_201));
    }

    @Operation(summary = "Get all test documents", description = "Retrieves a list of all test documents")
    @ApiResponse(responseCode = "200", description = "List of documents returned")
    @GetMapping
    public ResponseEntity<List<TestDocumentsDTO>> getAllDocuments() {
        return ResponseEntity.ok(testDocumentsService.getAllTestDocuments());
    }

    @Operation(summary = "Get documents by test ID", description = "Retrieves all documents associated with a specific test")
    @ApiResponse(responseCode = "200", description = "List of documents for given test ID returned")
    @GetMapping("/by-test/{testId}")
    public ResponseEntity<List<TestDocumentsDTO>> getDocumentsByTestId(@PathVariable Long testId) {
        return ResponseEntity.ok(testDocumentsService.getDocumentsByTestId(testId));
    }

    @Operation(summary = "Update a test document", description = "Updates details of an existing test document")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Document updated successfully"),
            @ApiResponse(responseCode = "417", description = "Update operation failed"),
            @ApiResponse(responseCode = "404", description = "Document not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateTestDocument(
            @PathVariable Long id,
            @RequestBody TestDocumentsDTO testDocumentsDTO) {
        TestDocumentsDTO updated = testDocumentsService.updateTestDocument(id, testDocumentsDTO);
        if (updated != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ReportsConstants.STATUS_200, ReportsConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ReportsConstants.STATUS_417, ReportsConstants.MESSAGE_417_UPDATE));
    }

    @Operation(summary = "Delete a test document", description = "Deletes a document by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Document deleted successfully"),
            @ApiResponse(responseCode = "417", description = "Delete operation failed"),
            @ApiResponse(responseCode = "404", description = "Document not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteTestDocument(@PathVariable Long id) {
        boolean deleted = testDocumentsService.deleteTestDocument(id);
        if (deleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ReportsConstants.STATUS_200, ReportsConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ReportsConstants.STATUS_417, ReportsConstants.MESSAGE_417_DELETE));
    }

    @Operation(
            summary = "Fetch a single test document",
            description = "Retrieves details of a single test document by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Document details returned"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TestDocumentsDTO> getTestDocumentById(@PathVariable Long id) {
        TestDocumentsDTO document = testDocumentsService.getTestDocumentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(document);
    }
}

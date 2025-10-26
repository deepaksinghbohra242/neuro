package com.neuromed.reports.controller;

import com.neuromed.reports.constants.ReportsConstants;
import com.neuromed.reports.dto.ErrorResponseDto;
import com.neuromed.reports.dto.ReportDTO;
import com.neuromed.reports.dto.ResponseDto;
import com.neuromed.reports.service.IReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Report Management",
        description = "CRUD REST APIs for managing report records"
)
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private IReportService reportService;

    @Operation(summary = "Create a new report", description = "Adds a new report record")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Report created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/createReport")
    public ResponseEntity<ResponseDto> createReport(@RequestBody ReportDTO reportDTO) {
        reportService.createReport(reportDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ReportsConstants.STATUS_201, ReportsConstants.MESSAGE_201));
    }

    @Operation(summary = "Get all reports", description = "Retrieves a list of all report records")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of reports returned")
    })
    @GetMapping("/fetchReports")
    public ResponseEntity<List<ReportDTO>> getReports() {
        return ResponseEntity.ok(reportService.getReports());
    }

    @Operation(summary = "Update a report", description = "Updates details of an existing report record")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Report updated successfully"),
            @ApiResponse(responseCode = "417", description = "Update operation failed"),
            @ApiResponse(responseCode = "404", description = "Report not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateReport(@PathVariable Long id, @RequestBody ReportDTO reportDTO) {
        ReportDTO updated = reportService.updateReport(id, reportDTO);
        if (updated != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ReportsConstants.STATUS_200, ReportsConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ReportsConstants.STATUS_417, ReportsConstants.MESSAGE_417_UPDATE));
    }

    @Operation(summary = "Delete a report", description = "Deletes a report record by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Report deleted successfully"),
            @ApiResponse(responseCode = "417", description = "Delete operation failed"),
            @ApiResponse(responseCode = "404", description = "Report not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteReport(@PathVariable Long id) {
        boolean deleted = reportService.deleteReport(id);
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
            summary = "Fetch Report",
            description = "Fetches report details based on report ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<ReportDTO> fetchReport(
            @RequestHeader("neuromed-correlation-id") String correlationId,
            @RequestParam String reportId) {
        ReportDTO reportDTO = reportService.fetchReport(reportId, correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(reportDTO);
    }
}

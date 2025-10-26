package com.neuromed.consultants.controller;

import com.neuromed.consultants.constants.ConsultantConstants;
import com.neuromed.consultants.dto.ConsultantEducationsDTO;
import com.neuromed.consultants.dto.ErrorResponseDto;
import com.neuromed.consultants.dto.ResponseDto;
import com.neuromed.consultants.service.IConsultantEducationsService;
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
        name = "Consultant Educations Management",
        description = "CRUD REST APIs for managing consultant education records"
)
@RestController
@RequestMapping("/api/educations")
public class ConsultantEducationsController {

    @Autowired
    private IConsultantEducationsService consultantEducationsService;

    @Operation(summary = "Create a new consultant education", description = "Adds a new education record")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Education record created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<ResponseDto> createEducation(@RequestBody ConsultantEducationsDTO educationDTO) {
        consultantEducationsService.createConsultantEducation(educationDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ConsultantConstants.STATUS_201, ConsultantConstants.MESSAGE_201));
    }

    @Operation(summary = "Get all consultant educations", description = "Retrieves all consultant education records")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of education records returned")
    })
    @GetMapping
    public ResponseEntity<List<ConsultantEducationsDTO>> getAllEducations() {
        return ResponseEntity.ok(consultantEducationsService.getAllConsultantEducations());
    }

    @Operation(summary = "Update consultant education", description = "Updates an existing education record")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Education record updated successfully"),
            @ApiResponse(responseCode = "417", description = "Update operation failed"),
            @ApiResponse(responseCode = "404", description = "Education record not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateEducation(@PathVariable Long id, @RequestBody ConsultantEducationsDTO educationDTO) {
        ConsultantEducationsDTO updated = consultantEducationsService.updateConsultantEducation(id, educationDTO);
        if (updated != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ConsultantConstants.STATUS_200, ConsultantConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ConsultantConstants.STATUS_417, ConsultantConstants.MESSAGE_417_UPDATE));
    }

    @Operation(summary = "Delete consultant education", description = "Deletes an education record by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Education record deleted successfully"),
            @ApiResponse(responseCode = "417", description = "Delete operation failed"),
            @ApiResponse(responseCode = "404", description = "Education record not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteEducation(@PathVariable Long id) {
        boolean deleted = consultantEducationsService.deleteConsultantEducation(id);
        if (deleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ConsultantConstants.STATUS_200, ConsultantConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ConsultantConstants.STATUS_417, ConsultantConstants.MESSAGE_417_DELETE));
    }

    @Operation(
            summary = "Fetch consultant education by ID",
            description = "Fetches education details for a consultant by education ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Education record found"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<ConsultantEducationsDTO> fetchEducation(
            @RequestHeader("neuromed-correlation-id") String correlationId,
            @RequestParam Long id) {
        ConsultantEducationsDTO educationDTO = consultantEducationsService.fetchConsultantEducation(id, correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(educationDTO);
    }
}

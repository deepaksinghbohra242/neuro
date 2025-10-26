package com.neuromed.consultants.controller;

import com.neuromed.consultants.constants.ConsultantConstants;
import com.neuromed.consultants.dto.ConsultantCertificationsDTO;

import com.neuromed.consultants.dto.ConsultantsContactInfoDto;
import com.neuromed.consultants.dto.ErrorResponseDto;
import com.neuromed.consultants.dto.ResponseDto;
import com.neuromed.consultants.service.IConsultantCertificationsService;
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
        name = "ConsultantCertifications Management",
        description = "CRUD REST APIs for managing consultants"
)
@RestController
@RequestMapping("/api")
public class ConsultantCertificationsController {

    @Autowired
    private IConsultantCertificationsService certificationsService;

    @Autowired
    private ConsultantsContactInfoDto consultantsContactInfoDto;

    @Operation(summary = "Create a new consultantCertifications", description = "Adds a new consultantCertifications to the system")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "ConsultantCertifications created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/createCertification")
    public ResponseEntity<ResponseDto> createConsultantCertifications(@RequestBody ConsultantCertificationsDTO certificationsDTO) {
        certificationsService.createConsultantCertifications(certificationsDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ConsultantConstants.STATUS_201, ConsultantConstants.MESSAGE_201));
    }

    @Operation(summary = "Get all ConsultantCertifications", description = "Retrieves a list of all consultants")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of consultants returned")
    })
    @GetMapping("/consultants")
    public ResponseEntity<List<ConsultantCertificationsDTO>> getConsultantCertifications() {
        return ResponseEntity.ok(certificationsService.getConsultantCertifications());
    }

    @Operation(summary = "Update a consultantCertifications", description = "Updates details of an existing consultants")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consultants updated successfully"),
            @ApiResponse(responseCode = "417", description = "Update operation failed"),
            @ApiResponse(responseCode = "404", description = "consultants not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateConsultant(@PathVariable Long id, @RequestBody ConsultantCertificationsDTO certificationsDTO) {
        ConsultantCertificationsDTO consultantCertificationsDTO = certificationsService.updateConsultantCertifications(id, certificationsDTO);
        if (consultantCertificationsDTO != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ConsultantConstants.STATUS_200, ConsultantConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ConsultantConstants.STATUS_417, ConsultantConstants.MESSAGE_417_UPDATE));
    }

    @Operation(summary = "Delete a consultant Certifications", description = "Deletes a consultant by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consultant deleted successfully"),
            @ApiResponse(responseCode = "417", description = "Delete operation failed"),
            @ApiResponse(responseCode = "404", description = "Consultant not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteConsultants(@PathVariable Long id) {
        boolean deleted = certificationsService.deleteConsultantCertifications(id);
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
            summary = "Get ConsultantCertifications Contact Info",
            description = "Contact info for consultants in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contact info returned"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/contact-info")
    public ResponseEntity<ConsultantsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(consultantsContactInfoDto);
    }

    @Operation(
            summary = "Fetch ConsultantCertifications Details REST API",
            description = "REST API to fetch ConsultantCertifications details based on ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetchCertification")
    public ResponseEntity<ConsultantCertificationsDTO> fetchConsultants(
            @RequestHeader("neuromed-correlation-id") String correlationId,
            @RequestParam Long id) {
        ConsultantCertificationsDTO consultantCertificationsDTO = certificationsService.fetchConsultantCertifications(id, correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(consultantCertificationsDTO);
    }
}


package com.neuromed.patients.controller;

import com.neuromed.patients.constants.PatientsConstants;
import com.neuromed.patients.dto.ErrorResponseDto;
import com.neuromed.patients.dto.ResponseDto;
import com.neuromed.patients.entity.Treatment;
import com.neuromed.patients.service.TreatmentService;
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

@Tag(name = "Treatment Management", description = "CRUD REST APIs for managing treatments")
@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {

  @Autowired
  private TreatmentService treatmentService;

  @Operation(summary = "Create a new treatment", description = "Adds a new treatment to the system")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Treatment created successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createTreatment(@RequestBody Treatment treatment) {
    treatmentService.createTreatment(treatment);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new ResponseDto(PatientsConstants.STATUS_201, "Treatment created successfully"));
  }

  @Operation(summary = "Get all treatments", description = "Retrieves a list of all treatments")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "List of treatments returned successfully")
  })
  @GetMapping
  public ResponseEntity<List<Treatment>> getAllTreatments() {
    return ResponseEntity.ok(treatmentService.getAllTreatments());
  }

  @Operation(summary = "Get treatment by ID", description = "Fetches details of a treatment by its ID")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Treatment details returned successfully"),
      @ApiResponse(responseCode = "404", description = "Treatment not found", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
  })
  @GetMapping("/{id}")
  public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long id) {
    Treatment treatment = treatmentService.getTreatmentById(id);
    return ResponseEntity.status(HttpStatus.OK).body(treatment);
  }

  @Operation(summary = "Update a treatment", description = "Updates details of an existing treatment")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Treatment updated successfully"),
      @ApiResponse(responseCode = "404", description = "Treatment not found", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
      @ApiResponse(responseCode = "417", description = "Update operation failed")
  })
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateTreatment(@PathVariable Long id, @RequestBody Treatment treatment) {
    Treatment updated = treatmentService.updateTreatment(id, treatment);
    if (updated != null) {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new ResponseDto(PatientsConstants.STATUS_200, "Treatment updated successfully"));
    }
    return ResponseEntity
        .status(HttpStatus.EXPECTATION_FAILED)
        .body(new ResponseDto(PatientsConstants.STATUS_417, "Treatment update failed"));
  }

  @Operation(summary = "Delete a treatment", description = "Deletes a treatment by its ID")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Treatment deleted successfully"),
      @ApiResponse(responseCode = "404", description = "Treatment not found", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
      @ApiResponse(responseCode = "417", description = "Delete operation failed")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteTreatment(@PathVariable Long id) {
    boolean deleted = treatmentService.deleteTreatment(id);
    if (deleted) {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new ResponseDto(PatientsConstants.STATUS_200, "Treatment deleted successfully"));
    }
    return ResponseEntity
        .status(HttpStatus.EXPECTATION_FAILED)
        .body(new ResponseDto(PatientsConstants.STATUS_417, "Treatment delete failed"));
  }
}

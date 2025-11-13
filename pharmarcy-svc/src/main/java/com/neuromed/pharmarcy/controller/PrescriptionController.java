package com.neuromed.pharmarcy.controller;

import com.neuromed.pharmarcy.constants.PrescriptionConstants;
import com.neuromed.pharmarcy.dto.PrescriptionDTO;
import com.neuromed.pharmarcy.dto.PrescriptionDetailDTO;
import com.neuromed.pharmarcy.dto.ReorderRequestDTO;
import com.neuromed.pharmarcy.dto.ResponseDTO;
import com.neuromed.pharmarcy.execption.PrescriptionNotFoundException;
import com.neuromed.pharmarcy.service.IPrescriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CRUD REST APIs for Prescriptions", description = "CRUD REST APIs to CREATE, LIST, FETCH DETAILS, and REORDER prescriptions")
@RestController
@RequestMapping("/api")
public class PrescriptionController {

  @Autowired
  private IPrescriptionService prescriptionService;

  @Operation(summary = "Create Prescription", description = "Create a new prescription (with or without medicines)")
  @ApiResponses({
      @ApiResponse(responseCode = PrescriptionConstants.STATUS_201, description = PrescriptionConstants.PRESCRIPTION_CREATED),
      @ApiResponse(responseCode = PrescriptionConstants.STATUS_400, description = PrescriptionConstants.MESSAGE_400),
      @ApiResponse(responseCode = PrescriptionConstants.STATUS_500, description = PrescriptionConstants.INTERNAL_SERVER_ERROR)
  })
  @PostMapping("/create")
  public ResponseEntity<ResponseDTO> createPrescription(
      @RequestHeader("neuromed-correlation-id") String correlationId,
      @RequestBody PrescriptionDTO dto) {

    prescriptionService.createPrescription(dto);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new ResponseDTO(
            PrescriptionConstants.STATUS_201,
            PrescriptionConstants.PRESCRIPTION_CREATED));
  }

  @Operation(summary = "List Prescriptions", description = "Fetch all prescriptions")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "List of prescriptions returned successfully"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  @GetMapping("/list")
  public ResponseEntity<List<PrescriptionDTO>> list(@RequestHeader("neuromed-correlation-id") String correlationId) {
    List<PrescriptionDTO> prescriptions = prescriptionService.listPrescriptions(correlationId);
    return ResponseEntity.ok(prescriptions);
  }

  @Operation(summary = "Fetch Prescription Details", description = "Fetch prescription details by ID")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Prescription details returned successfully"),
      @ApiResponse(responseCode = "404", description = "Prescription not found"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  @GetMapping("/detail/{id}")
  public ResponseEntity<PrescriptionDetailDTO> detail(@RequestHeader("neuromed-correlation-id") String correlationId,
      @PathVariable Long id) {
    PrescriptionDetailDTO prescription = prescriptionService.getPrescriptionDetailById(id, correlationId);
    if (prescription == null) {
      throw new PrescriptionNotFoundException(PrescriptionConstants.PRESCRIPTION_NOT_FOUND);
    }
    return ResponseEntity.ok(prescription);
  }

  @Operation(summary = "Reorder Prescription", description = "Reorder an existing prescription")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Prescription reordered successfully"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })

  @PostMapping("/reorder")
  public ResponseEntity<ResponseDTO> reorder(@RequestBody ReorderRequestDTO dto) {
    PrescriptionDTO prescription = prescriptionService.reorderPrescription(dto);
    if (prescription == null) {
      throw new RuntimeException(PrescriptionConstants.INTERNAL_SERVER_ERROR);
    }
    return ResponseEntity
        .ok(new ResponseDTO(PrescriptionConstants.PRESCRIPTION_REORDERED, PrescriptionConstants.STATUS_200));
  }

  @Operation(summary = "List Prescriptions by Status", description = "Fetch prescriptions filtered by their current status (NEW, RETURNED, REJECTED)")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "List of prescriptions filtered by status"),
      @ApiResponse(responseCode = "400", description = "Invalid status provided"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  @GetMapping("/listByStatus")
  public ResponseEntity<List<PrescriptionDTO>> listByStatus(
      @RequestHeader("neuromed-correlation-id") String correlationId,
      @RequestParam("status") String status) {

    List<PrescriptionDTO> prescriptions = prescriptionService.listPrescriptionsByStatus(correlationId, status);
    return ResponseEntity.ok(prescriptions);
  }
}

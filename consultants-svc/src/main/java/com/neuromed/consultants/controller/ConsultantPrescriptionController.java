package com.neuromed.consultants.controller;

import com.neuromed.consultants.dto.PrescriptionDTO;
import com.neuromed.consultants.dto.PrescriptionDetailDTO;
import com.neuromed.consultants.dto.ReorderRequestDTO;
import com.neuromed.consultants.dto.ResponseDto;
import com.neuromed.consultants.service.client.PrescriptionClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Tag(name = "Consultant Prescription APIs", description = "APIs for consultants to manage prescriptions via pharmacy service")
@RestController
@RequestMapping("/consultants/pat_prescriptions")
public class ConsultantPrescriptionController {

  @Autowired
  private PrescriptionClient prescriptionClient;

  @Operation(summary = "Fetch total prescription list for a patient")
  @GetMapping("/list")
  public ResponseEntity<List<PrescriptionDTO>> getPrescriptionList(
      @RequestHeader("neuromed-correlation-id") String correlationId) {

    List<PrescriptionDTO> prescriptions = prescriptionClient.getPrescriptions(correlationId);
    return ResponseEntity.ok(prescriptions);
  }

  @Operation(summary = "Create a new prescription")
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createPrescription(
      @RequestHeader("neuromed-correlation-id") String correlationId,
      @RequestBody PrescriptionDTO dto) {

    ResponseDto response = prescriptionClient.createPrescription(correlationId, dto);
    return ResponseEntity.ok(response);
  }

  @Operation(summary = "Fetch prescription details by ID")
  @GetMapping("/{id}")
  public ResponseEntity<PrescriptionDetailDTO> getPrescriptionDetails(
      @RequestHeader("neuromed-correlation-id") String correlationId,
      @PathVariable("id") Long id) {

    PrescriptionDetailDTO details = prescriptionClient.getPrescriptionDetail(correlationId, id);
    return ResponseEntity.ok(details);
  }

  @Operation(summary = "Reorder an existing prescription")
  @PostMapping("/reorder")
  public ResponseEntity<ResponseDto> reorderPrescription(
      @RequestHeader("neuromed-correlation-id") String correlationId,
      @RequestBody ReorderRequestDTO dto) {

    ResponseDto response = prescriptionClient.reorderPrescription(correlationId, dto);
    return ResponseEntity.ok(response);
  }
}

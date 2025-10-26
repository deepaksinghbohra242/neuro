package com.neuromed.pharmarcy.controller;

import com.neuromed.pharmarcy.constants.PharmacyConstants;
import com.neuromed.pharmarcy.dto.PrescriptionMedicineDTO;
import com.neuromed.pharmarcy.dto.ResponseDTO;
import com.neuromed.pharmarcy.dto.ErrorResponseDTO;
import com.neuromed.pharmarcy.service.IPrescriptionMedicineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Prescription Medicines",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH, and DELETE pharmacy medicine records"
)
@RestController
@RequestMapping("/api/prescription-medicines")
@Validated
@RequiredArgsConstructor
public class PrescriptionMedicinesController {

    private final IPrescriptionMedicineService prescriptionMedicineService;

    @Operation(summary = "Create Prescription Medicine", description = "Create a new pharmacy medicine record")
    @ApiResponses({
            @ApiResponse(responseCode = PharmacyConstants.STATUS_201, description = PharmacyConstants.MESSAGE_201),
            @ApiResponse(responseCode = PharmacyConstants.STATUS_500, description = PharmacyConstants.MESSAGE_500)
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createPrescriptionMedicine(
            @Valid @RequestBody PrescriptionMedicineDTO pharmacyMedicineDTO) {

        prescriptionMedicineService.createPrescriptionMedicine(pharmacyMedicineDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(PharmacyConstants.STATUS_201, PharmacyConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Prescription Medicine", description = "Fetch pharmacy medicine record by ID")
    @ApiResponses({
            @ApiResponse(responseCode = PharmacyConstants.STATUS_200, description = PharmacyConstants.MESSAGE_200),
            @ApiResponse(responseCode = PharmacyConstants.STATUS_500, description = PharmacyConstants.MESSAGE_500)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionMedicineDTO> fetchPrescriptionMedicine(@PathVariable Long id) {
        PrescriptionMedicineDTO dto = prescriptionMedicineService.fetchPrescriptionMedicine(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Fetch All Prescription Medicines", description = "Fetch all prescription medicine records")
    @ApiResponses({
            @ApiResponse(responseCode = PharmacyConstants.STATUS_200, description = PharmacyConstants.MESSAGE_200),
            @ApiResponse(responseCode = PharmacyConstants.STATUS_500, description = PharmacyConstants.MESSAGE_500)
    })
    @GetMapping("/all")
    public ResponseEntity<List<PrescriptionMedicineDTO>> getAllPrescriptionMedicines() {
        List<PrescriptionMedicineDTO> medicines = prescriptionMedicineService.getAllPrescriptionMedicines();
        return ResponseEntity.ok(medicines);
    }

    @Operation(summary = "Update Prescription Medicine", description = "Update existing prescription medicine record")
    @ApiResponses({
            @ApiResponse(responseCode = PharmacyConstants.STATUS_200, description = PharmacyConstants.MESSAGE_200),
            @ApiResponse(responseCode = PharmacyConstants.STATUS_417, description = PharmacyConstants.MESSAGE_417_UPDATE),
            @ApiResponse(responseCode = PharmacyConstants.STATUS_500, description = PharmacyConstants.MESSAGE_500)
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updatePrescriptionMedicine(
            @Valid @RequestBody PrescriptionMedicineDTO pharmacyMedicineDTO) {

        boolean isUpdated = prescriptionMedicineService.updatePrescriptionMedicine(pharmacyMedicineDTO);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(PharmacyConstants.STATUS_200, PharmacyConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(PharmacyConstants.STATUS_417, PharmacyConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(summary = "Delete Prescription Medicine", description = "Delete prescription medicine record by ID")
    @ApiResponses({
            @ApiResponse(responseCode = PharmacyConstants.STATUS_200, description = PharmacyConstants.MESSAGE_200),
            @ApiResponse(responseCode = PharmacyConstants.STATUS_417, description = PharmacyConstants.MESSAGE_417_DELETE),
            @ApiResponse(responseCode = PharmacyConstants.STATUS_500, description = PharmacyConstants.MESSAGE_500)
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deletePrescriptionMedicine(@PathVariable Long id) {
        boolean isDeleted = prescriptionMedicineService.deletePrescriptionMedicine(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(PharmacyConstants.STATUS_200, PharmacyConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(PharmacyConstants.STATUS_417, PharmacyConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Fetch prescription Medicine Details (with correlation ID)",
            description = "Fetch detailed prescription medicine information for logging/tracing"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            )
    })
    @GetMapping("/fetch-prescription")
    public ResponseEntity<PrescriptionMedicineDTO> fetchPrescriptionMedicineWithCorrelationId(
            @RequestHeader("neuromed-correlation-id") String correlationId,
            @RequestParam Long id) {

        PrescriptionMedicineDTO dto = prescriptionMedicineService.fetchPrescriptionMedicine(id, correlationId);
        return ResponseEntity.ok(dto);
    }
    @Operation(summary = "Fetch Medicines by Prescription ID",
            description = "Fetch all medicines associated with a given prescription ID")
    @ApiResponses({
            @ApiResponse(responseCode = PharmacyConstants.STATUS_200, description = PharmacyConstants.MESSAGE_200),
            @ApiResponse(responseCode = PharmacyConstants.STATUS_500, description = PharmacyConstants.MESSAGE_500)
    })
    @GetMapping("/by-prescription/{prescriptionId}")
    public ResponseEntity<List<PrescriptionMedicineDTO>> getMedicinesByPrescriptionId(
            @PathVariable("prescriptionId") Long prescriptionId) {

        List<PrescriptionMedicineDTO> medicines = prescriptionMedicineService.getMedicinesByPrescriptionId(prescriptionId);
        return ResponseEntity.ok(medicines);
    }

}

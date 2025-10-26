package com.neuromed.pharmarcy.controller;

import com.neuromed.pharmarcy.dto.ErrorResponseDTO;
import com.neuromed.pharmarcy.dto.PrescriptionDTO;
import com.neuromed.pharmarcy.dto.PrescriptionDetailDTO;
import com.neuromed.pharmarcy.execption.PrescriptionNotFoundException;
import com.neuromed.pharmarcy.service.IPrescriptionService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Prescription Management APIs",
        description = "APIs to CREATE, READ, UPDATE and DELETE Prescriptions"
)
@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionsController {

    private final IPrescriptionService prescriptionService;

    @Operation(summary = "Create Prescription", description = "Creates a new prescription")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Prescription created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping(value = "/createPrescription")
    public ResponseEntity<PrescriptionDTO> createPrescription(@Valid @RequestBody PrescriptionDTO prescriptionDTO) {
        PrescriptionDTO created = prescriptionService.createPrescription(prescriptionDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/getPrescription/{id}")
    public ResponseEntity<PrescriptionDTO> getPrescription(@PathVariable Long id) {
        PrescriptionDTO prescription = prescriptionService.getPrescriptionById(id);
        if (prescription != null) {
            return ResponseEntity.ok(prescription);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Get All Prescriptions", description = "Retrieves a list of all prescriptions")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of prescriptions fetched successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping(value = "/fetchPrescription")
    public ResponseEntity<List<PrescriptionDTO>> getAllPrescriptions() {
        List<PrescriptionDTO> prescriptions = prescriptionService.getAllPrescriptions();
        return ResponseEntity.ok(prescriptions);
    }

    @Operation(summary = "Get Prescription by ID", description = "Fetches prescription details by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prescription found"),
            @ApiResponse(responseCode = "404", description = "Prescription not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> getPrescriptionById(@PathVariable Long id) {
        PrescriptionDTO prescription = prescriptionService.getPrescriptionById(id);
        return ResponseEntity.ok(prescription);
    }

    @Operation(summary = "Update Prescription", description = "Updates prescription details by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prescription updated successfully"),
            @ApiResponse(responseCode = "404", description = "Prescription not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> updatePrescription(
            @PathVariable Long id,
            @Valid @RequestBody PrescriptionDTO prescriptionDTO) {
        PrescriptionDTO updated = prescriptionService.updatePrescription(id, prescriptionDTO);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete Prescription", description = "Deletes a prescription by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prescription deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Prescription not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrescription(@PathVariable Long id) {
        boolean deleted = prescriptionService.deletePrescription(id);
        if (deleted) {
            return ResponseEntity.ok("Prescription deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prescription not found");
        }
    }

//    @Operation(
//            summary = "Get Prescription Detail by ID",
//            description = "Fetches detailed prescription information including associated medicines by prescription ID"
//    )
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Prescription detail fetched successfully",
//                    content = @Content(schema = @Schema(implementation = PrescriptionDetailDTO.class))
//            ),
//            @ApiResponse(
//                    responseCode = "404",
//                    description = "Prescription not found",
//                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
//            )
//    })
//    @GetMapping("/prescriptionDetail/{id}")
//    public ResponseEntity<PrescriptionDetailDTO> getPrescriptionDetail(@PathVariable Long id) {
//        PrescriptionDetailDTO detail = prescriptionService.getPrescriptionDetailById(id);
//        if (detail == null) {
//            throw new PrescriptionNotFoundException("Prescription not found with id: " + id);
//        }
//        return ResponseEntity.ok(detail);
//    }
}

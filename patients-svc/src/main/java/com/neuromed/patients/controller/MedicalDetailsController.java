package com.neuromed.patients.controller;

import com.neuromed.patients.constants.MedicalConstants;
import com.neuromed.patients.dto.ErrorResponseDto;
import com.neuromed.patients.dto.MedicalDetailsDTO;
import com.neuromed.patients.dto.ResponseDto;
import com.neuromed.patients.exception.ResourceNotFoundException;
import com.neuromed.patients.service.IMedicalDetailsService;
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
        name = "Medical Details Management",
        description = "CRUD REST APIs for managing medical details of patients"
)
@RestController
@RequestMapping("/api/medical")
public class MedicalDetailsController {

    @Autowired
    private IMedicalDetailsService medicalService;

    // ✅ CREATE Medical Details
    @Operation(summary = "Create Medical Details", description = "Adds a new set of medical details for a patient")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Medical details created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createMedicalDetails(@RequestBody MedicalDetailsDTO medicalDetailsDto) {
        medicalService.createMedicalDetails(medicalDetailsDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(MedicalConstants.STATUS_201, MedicalConstants.MESSAGE_201));
    }

    // ✅ GET ALL Medical Details
    @Operation(summary = "Get all Medical Details", description = "Retrieves all medical details records")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of medical details returned successfully")
    })
    @GetMapping("/list")
    public ResponseEntity<List<MedicalDetailsDTO>> getAllMedicalDetails() {
        List<MedicalDetailsDTO> detailsList = medicalService.getAllMedicalDetails();
        return ResponseEntity.status(HttpStatus.OK).body(detailsList);
    }

    // ✅ GET Medical Details by ID
    @Operation(summary = "Fetch Medical Details", description = "Fetch medical details using medical ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Medical details fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Medical details not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<MedicalDetailsDTO> getMedicalDetailsById(@PathVariable Long id) {
        MedicalDetailsDTO details = medicalService.getMedicalDetailsById(id);
        if (details == null) {
            throw new ResourceNotFoundException("MedicalDetails", "id", String.valueOf(id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(details);
    }

    // ✅ UPDATE Medical Details
    @Operation(summary = "Update Medical Details", description = "Update existing medical details for a patient")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Medical details updated successfully"),
            @ApiResponse(responseCode = "404", description = "Medical details not found"),
            @ApiResponse(responseCode = "417", description = "Update operation failed")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateMedicalDetails(@PathVariable Long id, @RequestBody MedicalDetailsDTO medicalDetailsDto) {
        MedicalDetailsDTO updated = medicalService.updateMedicalDetails(id, medicalDetailsDto);
        if (updated != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(MedicalConstants.STATUS_200, MedicalConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto("417", "Update operation failed"));
    }

    // ✅ DELETE Medical Details
    @Operation(summary = "Delete Medical Details", description = "Delete medical details by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Medical details deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Medical details not found"),
            @ApiResponse(responseCode = "417", description = "Delete operation failed")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteMedicalDetails(@PathVariable Long id) {
        boolean deleted = medicalService.deleteMedicalDetails(id);
        if (deleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(MedicalConstants.STATUS_200, MedicalConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto("417", "Update operation failed"));
    }
}

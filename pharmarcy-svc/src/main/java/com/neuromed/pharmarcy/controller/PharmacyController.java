package com.neuromed.pharmarcy.controller;


import com.neuromed.pharmarcy.constants.PharmacyConstants;
import com.neuromed.pharmarcy.dto.*;
import com.neuromed.pharmarcy.service.IPharmacyService;
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
        name = "CRUD REST APIs for Pharmacy Management",
        description = "CRUD REST APIs for managing pharmacy records"
)
@RestController
@RequestMapping("/api/pharmacies")
public class PharmacyController {

    @Autowired
    private IPharmacyService pharmacyService;

    @Autowired
    private PharmacyContactInfoDto pharmacyContactInfoDto;

    @Operation(summary = "Create a new pharmacy", description = "Adds a new pharmacy record")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pharmacy created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/createPharmacy")
    public ResponseEntity<ResponseDTO> createPharmacy(@RequestBody PharmacyDTO pharmacyDTO) {
        pharmacyService.createPharmacy(pharmacyDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(PharmacyConstants.STATUS_201, PharmacyConstants.MESSAGE_201));
    }

    @Operation(summary = "Get all pharmacies", description = "Retrieves a list of all pharmacy records")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of pharmacies returned")
    })
    @GetMapping("/fetchPharmacies")
    public ResponseEntity<List<PharmacyDTO>> getPharmacies() {
        return ResponseEntity.ok(pharmacyService.getPharmacies());
    }

    @Operation(summary = "Update a pharmacy", description = "Updates details of an existing pharmacy record")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pharmacy updated successfully"),
            @ApiResponse(responseCode = "417", description = "Update operation failed"),
            @ApiResponse(responseCode = "404", description = "Pharmacy not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updatePharmacy(@PathVariable Long id, @RequestBody PharmacyDTO pharmacyDTO) {
        PharmacyDTO updated = pharmacyService.updatePharmacy(id, pharmacyDTO);
        if (updated != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(PharmacyConstants.STATUS_200, PharmacyConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDTO(PharmacyConstants.STATUS_417, PharmacyConstants.MESSAGE_417_UPDATE));
    }

    @Operation(summary = "Delete a pharmacy", description = "Deletes a pharmacy record by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pharmacy deleted successfully"),
            @ApiResponse(responseCode = "417", description = "Delete operation failed"),
            @ApiResponse(responseCode = "404", description = "Pharmacy not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePharmacy(@PathVariable Long id) {
        boolean deleted = pharmacyService.deletePharmacy(id);
        if (deleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(PharmacyConstants.STATUS_200, PharmacyConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDTO(PharmacyConstants.STATUS_417, PharmacyConstants.MESSAGE_417_DELETE));
    }

    @Operation(
            summary = "Get Contact Info",
            description = "Returns contact information in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contact info returned"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/contact-info")
    public ResponseEntity<PharmacyContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pharmacyContactInfoDto);
    }

    @Operation(
            summary = "Fetch Pharmacy",
            description = "Fetches pharmacy details based on pharmacy ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<PharmacyDTO> fetchPharmacy(
            @RequestHeader("neuromed-correlation-id") String correlationId,
            @RequestParam String pharmacyId) {
        PharmacyDTO pharmacyDTO = pharmacyService.fetchPharmacy(pharmacyId, correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(pharmacyDTO);
    }

}

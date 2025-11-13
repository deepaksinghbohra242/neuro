package com.neuromed.patients.controller;

import com.neuromed.patients.constants.PatientsConstants;
import com.neuromed.patients.dto.*;
import com.neuromed.patients.service.IPatientService;
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

@Tag(name = "Patient Management", description = "CRUD REST APIs for managing patients")
@RestController
@RequestMapping("/api")
public class PatientController {

        @Autowired
        private IPatientService patientService;

        @Autowired
        private PatientsContactInfoDto patientsContactInfoDto;

        @Operation(summary = "Create a new patient", description = "Adds a new patient to the system")
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Patient created successfully"),
                        @ApiResponse(responseCode = "400", description = "Invalid input")
        })
        @PostMapping("/create")
        public ResponseEntity<ResponseDto> createPatient(@RequestBody PatientDTO patientDTO) {
                patientService.createPatient(patientDTO);
                return ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(new ResponseDto(PatientsConstants.STATUS_201, PatientsConstants.MESSAGE_201));
        }

        @Operation(summary = "Get all patients", description = "Retrieves a list of all patients")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "List of patients returned")
        })
        @GetMapping("/patients")
        public ResponseEntity<List<PatientDTO>> getPatients(@RequestHeader("neuromed-correlation-id") String correlationId) {
                return ResponseEntity.ok(patientService.getPatients(correlationId));
        }

        @Operation(summary = "Update a patient", description = "Updates details of an existing patient")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Patient updated successfully"),
                        @ApiResponse(responseCode = "417", description = "Update operation failed"),
                        @ApiResponse(responseCode = "404", description = "Patient not found")
        })
        @PutMapping("/{id}")
        public ResponseEntity<ResponseDto> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
                PatientDTO updated = patientService.updatePatient(id, patientDTO);
                if (updated != null) {
                        return ResponseEntity
                                        .status(HttpStatus.OK)
                                        .body(new ResponseDto(PatientsConstants.STATUS_200,
                                                        PatientsConstants.MESSAGE_200));
                }
                return ResponseEntity
                                .status(HttpStatus.EXPECTATION_FAILED)
                                .body(new ResponseDto(PatientsConstants.STATUS_417,
                                                PatientsConstants.MESSAGE_417_UPDATE));
        }

        @Operation(summary = "Delete a patient", description = "Deletes a patient by ID")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Patient deleted successfully"),
                        @ApiResponse(responseCode = "417", description = "Delete operation failed"),
                        @ApiResponse(responseCode = "404", description = "Patient not found")
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<ResponseDto> deletePatient(@PathVariable Long id) {
                boolean deleted = patientService.deletePatient(id);
                if (deleted) {
                        return ResponseEntity
                                        .status(HttpStatus.OK)
                                        .body(new ResponseDto(PatientsConstants.STATUS_200,
                                                        PatientsConstants.MESSAGE_200));
                }
                return ResponseEntity
                                .status(HttpStatus.EXPECTATION_FAILED)
                                .body(new ResponseDto(PatientsConstants.STATUS_417,
                                                PatientsConstants.MESSAGE_417_DELETE));
        }

        @Operation(summary = "Get Patient Contact Info", description = "Contact info for patients in case of any issues")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Contact info returned"),
                        @ApiResponse(responseCode = "500", description = "Internal Server Error")
        })
        @GetMapping("/contact-info")
        public ResponseEntity<PatientsContactInfoDto> getContactInfo() {
                return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(patientsContactInfoDto);
        }

        @Operation(summary = "Fetch Patient Details REST API", description = "REST API to fetch patient details based on patient ID")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                        @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
        })
        @GetMapping("/fetch")
        public ResponseEntity<PatientDetailsDTO> fetchPatientDetails(
                        @RequestHeader("neuromed-correlation-id") String correlationId,
                        @RequestParam("patientId") Long patientId) {
                PatientDetailsDTO patientDto = patientService.fetchPatient(patientId, correlationId);
                return ResponseEntity.status(HttpStatus.OK).body(patientDto);
        }

        @Operation(summary = "Get all patients by status", description = "Retrieves a list of all patients")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "List of patients returned")
        })
        @GetMapping("/patientsByStatus")
        public ResponseEntity<List<PatientDTO>> getPatientsByStatus(@RequestHeader("neuromed-correlation-id") String correlationId , @RequestParam("status") String status) {
                return ResponseEntity.ok(patientService.getPatientsByStatus(correlationId , status));
        }

}

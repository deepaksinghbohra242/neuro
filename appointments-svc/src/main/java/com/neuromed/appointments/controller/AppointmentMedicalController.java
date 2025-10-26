package com.neuromed.appointments.controller;
import com.neuromed.appointments.constants.AppointmentsConstants;
import com.neuromed.appointments.dto.AppointmentMedicalDetailsDTO;
import com.neuromed.appointments.dto.ErrorResponseDto;
import com.neuromed.appointments.dto.ResponseDto;
import com.neuromed.appointments.service.IAppointmentMedicalService;
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

    /**
     * REST Controller for Appointment Medical Details
     */
    @Tag(
            name = "CRUD REST APIs for Appointment Medical Details",
            description = "APIs to CREATE, UPDATE, FETCH, and DELETE appointment medical detail records"
    )
    @RestController
    @RequestMapping("/api/appointment-medical-details")
    @RequiredArgsConstructor
    public class AppointmentMedicalController {

        private final IAppointmentMedicalService appointmentMedicalService;

        // -------------------- CREATE --------------------
        @Operation(summary = "Create Appointment Medical Details", description = "Create new medical details for an appointment")
        @ApiResponses({
                @ApiResponse(responseCode = AppointmentsConstants.STATUS_201, description = AppointmentsConstants.MESSAGE_201),
                @ApiResponse(responseCode = AppointmentsConstants.STATUS_500, description = AppointmentsConstants.MESSAGE_500)
        })
        @PostMapping("/create")
        public ResponseEntity<ResponseDto> createMedicalDetails(
                @Valid @RequestBody AppointmentMedicalDetailsDTO dto) {
            appointmentMedicalService.createAppointmentMedicalDetails(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(AppointmentsConstants.STATUS_201, AppointmentsConstants.MESSAGE_201));
        }

        // -------------------- FETCH BY ID --------------------
        @Operation(summary = "Fetch Appointment Medical Details", description = "Fetch medical details by ID")
        @ApiResponses({
                @ApiResponse(responseCode = AppointmentsConstants.STATUS_200, description = AppointmentsConstants.MESSAGE_200),
                @ApiResponse(responseCode = AppointmentsConstants.STATUS_417, description = "Medical details not found"),
                @ApiResponse(responseCode = AppointmentsConstants.STATUS_500, description = AppointmentsConstants.MESSAGE_500)
        })
        @GetMapping("/{id}")
        public ResponseEntity<AppointmentMedicalDetailsDTO> fetchMedicalDetails(@PathVariable Long id) {
            AppointmentMedicalDetailsDTO dto = appointmentMedicalService.fetchAppointmentMedicalDetails(id);
            return ResponseEntity.ok(dto);
        }

        // -------------------- UPDATE --------------------
        @Operation(summary = "Update Appointment Medical Details", description = "Update existing appointment medical details")
        @ApiResponses({
                @ApiResponse(responseCode = AppointmentsConstants.STATUS_200, description = AppointmentsConstants.MESSAGE_200),
                @ApiResponse(responseCode = AppointmentsConstants.STATUS_417, description = AppointmentsConstants.MESSAGE_417_UPDATE),
                @ApiResponse(responseCode = AppointmentsConstants.STATUS_500, description = AppointmentsConstants.MESSAGE_500)
        })
        @PutMapping("/update")
        public ResponseEntity<ResponseDto> updateMedicalDetails(
                @Valid @RequestBody AppointmentMedicalDetailsDTO dto) {
            boolean isUpdated = appointmentMedicalService.updateAppointmentMedicalDetails(dto);
            if (isUpdated) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseDto(AppointmentsConstants.STATUS_200, AppointmentsConstants.MESSAGE_200));
            } else {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                        .body(new ResponseDto(AppointmentsConstants.STATUS_417, AppointmentsConstants.MESSAGE_417_UPDATE));
            }
        }

        // -------------------- DELETE --------------------
        @Operation(summary = "Delete Appointment Medical Details", description = "Delete medical details by ID")
        @ApiResponses({
                @ApiResponse(responseCode = AppointmentsConstants.STATUS_200, description = AppointmentsConstants.MESSAGE_200),
                @ApiResponse(responseCode = AppointmentsConstants.STATUS_417, description = AppointmentsConstants.MESSAGE_417_DELETE),
                @ApiResponse(responseCode = AppointmentsConstants.STATUS_500, description = AppointmentsConstants.MESSAGE_500)
        })
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<ResponseDto> deleteMedicalDetails(@PathVariable Long id) {
            boolean isDeleted = appointmentMedicalService.deleteAppointmentMedicalDetails(id);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseDto(AppointmentsConstants.STATUS_200, AppointmentsConstants.MESSAGE_200));
            } else {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                        .body(new ResponseDto(AppointmentsConstants.STATUS_417, AppointmentsConstants.MESSAGE_417_DELETE));
            }
        }

        // -------------------- FETCH WITH CORRELATION ID --------------------
        @Operation(
                summary = "Fetch Appointment Medical Details with Correlation ID",
                description = "Fetch medical details using correlation ID for distributed tracing"
        )
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal Server Error",
                        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                )
        })
        @GetMapping("/fetch")
        public ResponseEntity<AppointmentMedicalDetailsDTO> fetchWithCorrelation(
                @RequestHeader("neuromed-correlation-id") String correlationId,
                @RequestParam Long id) {
            AppointmentMedicalDetailsDTO dto = appointmentMedicalService.fetchAppointmentMedicalDetails(id, correlationId);
            return ResponseEntity.ok(dto);
        }
    }








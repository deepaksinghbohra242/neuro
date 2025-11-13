package com.neuromed.appointments.controller;

import com.neuromed.appointments.constants.AppointmentsConstants;
import com.neuromed.appointments.dto.*;
import com.neuromed.appointments.service.IAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for Appointments",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE appointment details"
)
@RestController
@RequestMapping("/api")
@Validated
public class AppointmentsController {

    private final IAppointmentService appointmentService;

    @Autowired
    private AppointmentsContactInfoDto appointmentsContactInfoDto;


    public AppointmentsController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Operation(summary = "Create Appointment", description = "Create a new appointment")
    @ApiResponses({
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_201, description = AppointmentsConstants.MESSAGE_201),
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_500, description = AppointmentsConstants.MESSAGE_500)
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAppointment(@Valid @RequestBody AppointmentDTO appointmentDto) {
        appointmentService.createAppointment(appointmentDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AppointmentsConstants.STATUS_201, AppointmentsConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Appointment", description = "Fetch appointment by ID")
    @ApiResponses({
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_200, description = AppointmentsConstants.MESSAGE_200),
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_500, description = AppointmentsConstants.MESSAGE_500)
    })
    @GetMapping("/fetch")
    public ResponseEntity<AppointmentDTO> fetchAppointment(@RequestHeader("neuromed-correlation-id") String correlationId, @RequestParam("appointmentId") Long appointmentId) {
        AppointmentDTO appointmentDto = appointmentService.fetchAppointment(correlationId, appointmentId);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentDto);
    }

    @Operation(summary = "Update Appointment", description = "Update appointment details")
    @ApiResponses({
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_200, description = AppointmentsConstants.MESSAGE_200),
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_417, description = AppointmentsConstants.MESSAGE_417_UPDATE),
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_500, description = AppointmentsConstants.MESSAGE_500)
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAppointment(@Valid @RequestBody AppointmentDTO appointmentDto) {
        boolean isUpdated = appointmentService.updateAppointment(appointmentDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AppointmentsConstants.STATUS_200, AppointmentsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AppointmentsConstants.STATUS_417, AppointmentsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(summary = "Delete Appointment", description = "Delete appointment by ID")
    @ApiResponses({
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_200, description = AppointmentsConstants.MESSAGE_200),
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_417, description = AppointmentsConstants.MESSAGE_417_DELETE),
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_500, description = AppointmentsConstants.MESSAGE_500)
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAppointment(@RequestParam Long appointmentId) {
        boolean isDeleted = appointmentService.deleteAppointment(appointmentId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AppointmentsConstants.STATUS_200, AppointmentsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AppointmentsConstants.STATUS_417, AppointmentsConstants.MESSAGE_417_DELETE));
        }
    }


    @Operation(
            summary = "Fetch Appointment Details REST API",
            description = "REST API to fetch Appointment and Patient details based on appointment ID"
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

    @GetMapping("/fetchAppointmentDetails")
    public ResponseEntity<AppointmentDetailsDTO> fetchAppointmentDetails(
            @RequestHeader("neuromed-correlation-id") String correlationId,
            @RequestParam("appointmentId") Long appointmentId) {
        AppointmentDetailsDTO detailsDTO = appointmentService.fetchAppointmentDetails(appointmentId, correlationId);
        return ResponseEntity.ok(detailsDTO);
    }

    @Operation(
            summary = "Contact Us API",
            description = "Provides contact information for appointment queries and support"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Contact information returned successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping(value = "/contact-us", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentsContactInfoDto> contactUs() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(appointmentsContactInfoDto);
    }

    @Operation(summary = "List all Appointments", description = "Fetch all appointment records")
        @GetMapping("/list")
        public ResponseEntity<List<AppointmentDTO>> listAppointments(@RequestHeader("neuromed-correlation-id") String correlationId) {
        List<AppointmentDTO> list = appointmentService.listAppointments(correlationId);
        return ResponseEntity.ok(list);
        }

    @Operation(summary = "List Appointments by Consultant ID", description = "Retrieve all appointments associated with a specific consultant.")
    @GetMapping("/listByConsultantId")
    public ResponseEntity<List<AppointmentDTO>> listAppointmentsByConsultantId(@RequestHeader("neuromed-correlation-id") String correlationId ,@RequestParam("consultantId") Long consultantId) {
        List<AppointmentDTO> list = appointmentService.listAppointmentsByConsultantId(correlationId , consultantId );
        return ResponseEntity.ok(list);
    }

    @GetMapping("/today")
    @Operation(summary = "Appointments Created Today", description = "Fetch all appointments created today")
    public ResponseEntity<List<AppointmentSummaryDTO>> getAppointmentsCreatedToday(
            @RequestHeader("neuromed-correlation-id") String correlationId) {
        List<AppointmentSummaryDTO> list = appointmentService.getAppointmentsCreatedToday(correlationId);
        return ResponseEntity.ok(list);
    }
}

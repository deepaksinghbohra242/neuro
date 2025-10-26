package com.neuromed.consultants.controller;

import com.neuromed.consultants.constants.ConsultantConstants;
import com.neuromed.consultants.dto.ErrorResponseDto;
import com.neuromed.consultants.dto.ResponseDto;
import com.neuromed.consultants.dto.ScheduleDto;
import com.neuromed.consultants.service.IScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Schedules",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE schedule details"
)
@RestController
@RequestMapping("/api/schedules")
@Validated
public class ScheduleController {

    private final IScheduleService scheduleService;

    @Autowired
    public ScheduleController(IScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // ✅ CREATE Schedule
    @Operation(summary = "Create Schedule", description = "Create a new schedule for a consultant")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Schedule record created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createSchedule(@Valid @RequestBody ScheduleDto scheduleDto) {
        scheduleService.createSchedule(scheduleDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(ConsultantConstants.STATUS_201, ConsultantConstants.MESSAGE_201));
    }

    // ✅ FETCH Schedule
    @Operation(summary = "Fetch Schedule", description = "Fetch schedule by ID")
    @ApiResponses({
            @ApiResponse(responseCode = ConsultantConstants.STATUS_200, description = ConsultantConstants.MESSAGE_200),
            @ApiResponse(responseCode = ConsultantConstants.STATUS_500, description = ConsultantConstants.MESSAGE_500)
    })
    @GetMapping("/fetch")
    public ResponseEntity<ScheduleDto> fetchSchedule(@RequestParam Long scheduleId) {
        ScheduleDto scheduleDto = scheduleService.fetchSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(scheduleDto);
    }

    // ✅ UPDATE Schedule
    @Operation(summary = "Update Schedule", description = "Update schedule details")
    @ApiResponses({
            @ApiResponse(responseCode = ConsultantConstants.STATUS_200, description = ConsultantConstants.MESSAGE_200),
            @ApiResponse(responseCode = ConsultantConstants.STATUS_417, description = ConsultantConstants.MESSAGE_417_UPDATE),
            @ApiResponse(responseCode = ConsultantConstants.STATUS_500, description = ConsultantConstants.MESSAGE_500)
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateSchedule(@Valid @RequestBody ScheduleDto scheduleDto) {
        boolean isUpdated = scheduleService.updateSchedule(scheduleDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(ConsultantConstants.STATUS_200, ConsultantConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ConsultantConstants.STATUS_417, ConsultantConstants.MESSAGE_417_UPDATE));
        }
    }

    // ✅ DELETE Schedule
    @Operation(summary = "Delete Schedule", description = "Delete schedule by ID")
    @ApiResponses({
            @ApiResponse(responseCode = ConsultantConstants.STATUS_200, description = ConsultantConstants.MESSAGE_200),
            @ApiResponse(responseCode = ConsultantConstants.STATUS_417, description = ConsultantConstants.MESSAGE_417_DELETE),
            @ApiResponse(responseCode = ConsultantConstants.STATUS_500, description = ConsultantConstants.MESSAGE_500)
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteSchedule(@RequestParam Long scheduleId) {
        boolean isDeleted = scheduleService.deleteSchedule(scheduleId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(ConsultantConstants.STATUS_200, ConsultantConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ConsultantConstants.STATUS_417, ConsultantConstants.MESSAGE_417_DELETE));
        }
    }

    // ✅ FETCH Schedule Details (with correlation ID)
    @Operation(
            summary = "Fetch Schedule Details REST API",
            description = "REST API to fetch schedule details based on schedule ID and correlation ID"
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
    @GetMapping("/fetchScheduleDetails")
    public ResponseEntity<ScheduleDto> fetchScheduleDetails(
            @RequestHeader("neuromed-correlation-id") String correlationId,
            @RequestParam Long scheduleId) {
        ScheduleDto detailsDto = scheduleService.fetchScheduleDetails(scheduleId, correlationId);
        return ResponseEntity.ok(detailsDto);
    }
}

package com.neuromed.appointments.controller;

import com.neuromed.appointments.constants.AppointmentOtherDetailsConstants;
import com.neuromed.appointments.dto.AppointmentOtherDetailsDTO;
import com.neuromed.appointments.dto.ResponseDto;
import com.neuromed.appointments.service.IAppointmentOtherDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Appointment Other Details API", description = "CRUD REST APIs for managing appointment other details")
@RestController
@RequestMapping("/api/appointment-other-details")
@RequiredArgsConstructor
@Validated
public class AppointmentOtherDetailsController {

    private final IAppointmentOtherDetailsService service;

    @Operation(summary = "Create Appointment Other Details", description = "Create new appointment other details")
    @ApiResponses({
            @ApiResponse(responseCode = AppointmentOtherDetailsConstants.STATUS_201, description = AppointmentOtherDetailsConstants.MESSAGE_201)
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody AppointmentOtherDetailsDTO dto) {
        service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AppointmentOtherDetailsConstants.STATUS_201, AppointmentOtherDetailsConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Appointment Other Details by ID", description = "Fetch appointment other details using ID")
    @ApiResponses({
            @ApiResponse(responseCode = AppointmentOtherDetailsConstants.STATUS_200, description = AppointmentOtherDetailsConstants.MESSAGE_200),
            @ApiResponse(responseCode = AppointmentOtherDetailsConstants.STATUS_404, description = AppointmentOtherDetailsConstants.MESSAGE_404)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentOtherDetailsDTO> getById(@PathVariable Long id) {
        AppointmentOtherDetailsDTO dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "List All Appointment Other Details", description = "Fetch all appointment other details")
    @ApiResponses({
            @ApiResponse(responseCode = AppointmentOtherDetailsConstants.STATUS_200, description = AppointmentOtherDetailsConstants.MESSAGE_200)
    })
    @GetMapping("/list")
    public ResponseEntity<List<AppointmentOtherDetailsDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @Operation(summary = "Update Appointment Other Details", description = "Update appointment other details by ID")
    @ApiResponses({
            @ApiResponse(responseCode = AppointmentOtherDetailsConstants.STATUS_200, description = AppointmentOtherDetailsConstants.MESSAGE_200),
            @ApiResponse(responseCode = AppointmentOtherDetailsConstants.STATUS_404, description = AppointmentOtherDetailsConstants.MESSAGE_404)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long id, @Valid @RequestBody AppointmentOtherDetailsDTO dto) {
        service.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(AppointmentOtherDetailsConstants.STATUS_200, AppointmentOtherDetailsConstants.MESSAGE_200));
    }

    @Operation(summary = "Delete Appointment Other Details", description = "Delete appointment other details by ID")
    @ApiResponses({
            @ApiResponse(responseCode = AppointmentOtherDetailsConstants.STATUS_200, description = AppointmentOtherDetailsConstants.MESSAGE_200),
            @ApiResponse(responseCode = AppointmentOtherDetailsConstants.STATUS_404, description = AppointmentOtherDetailsConstants.MESSAGE_404)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(AppointmentOtherDetailsConstants.STATUS_200, AppointmentOtherDetailsConstants.MESSAGE_200));
    }

    @Operation(summary = "Upload Documents for Appointment Other Details", description = "Upload one or more files for a given appointment other details record")
    @ApiResponses({
            @ApiResponse(responseCode = AppointmentOtherDetailsConstants.STATUS_200, description = AppointmentOtherDetailsConstants.MESSAGE_200)
    })
    @PostMapping("/{id}/upload-documents")
    public ResponseEntity<AppointmentOtherDetailsDTO> uploadDocuments(
            @PathVariable Long id,
            @RequestParam("files") List<MultipartFile> files) {
        AppointmentOtherDetailsDTO updated = service.uploadDocuments(id, files);
        return ResponseEntity.ok(updated);
    }
}

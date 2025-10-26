package com.neuromed.appointments.controller;

import com.neuromed.appointments.constants.AppointmentsConstants;
import com.neuromed.appointments.dto.AppointmemtsAddressDTO;
import com.neuromed.appointments.dto.ResponseDto;
import com.neuromed.appointments.service.IAppointmentAddressService;
import io.swagger.v3.oas.annotations.Operation;
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
        name = "CRUD REST APIs for Appointment Addresses",
        description = "REST APIs to CREATE, UPDATE, FETCH AND DELETE appointment address details"
)
@RestController
@RequestMapping("/api/addresses")
@Validated
public class AppointmentAddressController {

    private final IAppointmentAddressService appointmentAddressService;

    @Autowired
    public AppointmentAddressController(IAppointmentAddressService appointmentAddressService) {
        this.appointmentAddressService = appointmentAddressService;
    }

    @Operation(summary = "Create Appointment Address", description = "Create a new appointment address")
    @ApiResponses({
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_201, description = AppointmentsConstants.MESSAGE_201),
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_500, description = AppointmentsConstants.MESSAGE_500)
    })
    @PostMapping("/createAppointment")
    public ResponseEntity<ResponseDto> createAppointmentAddress(@Valid @RequestBody AppointmemtsAddressDTO addressDto) {
        appointmentAddressService.createAppointmentAddress(addressDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AppointmentsConstants.STATUS_201, AppointmentsConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Appointment Address", description = "Fetch appointment address by ID")
    @ApiResponses({
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_200, description = AppointmentsConstants.MESSAGE_200),
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_500, description = AppointmentsConstants.MESSAGE_500)
    })
    @GetMapping("/fetch")
    public ResponseEntity<AppointmemtsAddressDTO> fetchAppointmentAddress(@RequestParam Long addressId) {
        AppointmemtsAddressDTO dto = appointmentAddressService.fetchAppointmentAddress(addressId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Update Appointment Address", description = "Update an existing appointment address")
    @ApiResponses({
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_200, description = AppointmentsConstants.MESSAGE_200),
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_417, description = AppointmentsConstants.MESSAGE_417_UPDATE),
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_500, description = AppointmentsConstants.MESSAGE_500)
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAppointmentAddress(@Valid @RequestBody AppointmemtsAddressDTO addressDto) {
        boolean isUpdated = appointmentAddressService.updateAppointmentAddress(addressDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AppointmentsConstants.STATUS_200, AppointmentsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AppointmentsConstants.STATUS_417, AppointmentsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(summary = "Delete Appointment Address", description = "Delete an appointment address by ID")
    @ApiResponses({
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_200, description = AppointmentsConstants.MESSAGE_200),
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_417, description = AppointmentsConstants.MESSAGE_417_DELETE),
            @ApiResponse(responseCode = AppointmentsConstants.STATUS_500, description = AppointmentsConstants.MESSAGE_500)
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAppointmentAddress(@RequestParam Long addressId) {
        boolean isDeleted = appointmentAddressService.deleteAppointmentAddress(addressId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AppointmentsConstants.STATUS_200, AppointmentsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AppointmentsConstants.STATUS_417, AppointmentsConstants.MESSAGE_417_DELETE));
        }
    }
}

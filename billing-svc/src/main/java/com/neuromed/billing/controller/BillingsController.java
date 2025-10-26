package com.neuromed.billing.controller;

import com.neuromed.billing.constants.BillingConstants;
import com.neuromed.billing.dto.BillingDetailDTO;
import com.neuromed.billing.dto.BillingsDTO;
import com.neuromed.billing.dto.ResponseDto;
import com.neuromed.billing.service.IBillingsService;
import io.swagger.v3.oas.annotations.Operation;
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
        name = "CRUD REST APIs for Billings",
        description = "APIs for managing patient billing records"
)
@RestController
@RequestMapping("/api/billings")
@RequiredArgsConstructor
public class BillingsController {

    private final IBillingsService billingsService;

    @Operation(summary = "Create Billing Record", description = "Add new billing record for a patient")
    @ApiResponses({
            @ApiResponse(responseCode = BillingConstants.STATUS_201, description = BillingConstants.MESSAGE_201),
            @ApiResponse(responseCode = BillingConstants.STATUS_417, description = BillingConstants.MESSAGE_417_CREATE),
            @ApiResponse(responseCode = "400", description = "Bad request or validation error")
    })
    @PostMapping("/billing")
    public ResponseEntity<ResponseDto> createBillings(@Valid @RequestBody BillingsDTO dto) {
        billingsService.createBillings(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(BillingConstants.STATUS_201, BillingConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Billing by ID", description = "Retrieve a billing record by ID")
    @ApiResponses({
            @ApiResponse(responseCode = BillingConstants.STATUS_200, description = BillingConstants.MESSAGE_200),
            @ApiResponse(responseCode = BillingConstants.STATUS_404, description = BillingConstants.MESSAGE_404)
    })
    @GetMapping("/fetchBilling")
    public ResponseEntity<BillingDetailDTO> fetchBillings(
            @RequestHeader("neuromed-correlation-id") String correlationId,
            @RequestParam Long id) {
        BillingDetailDTO dto = billingsService.fetchBillings(id, correlationId);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Get All Billing Records", description = "Retrieve all billing records")
    @ApiResponses({
            @ApiResponse(responseCode = BillingConstants.STATUS_200, description = BillingConstants.MESSAGE_200)
    })
    @GetMapping("/billings")
    public ResponseEntity<List<BillingsDTO>> getAllBillings() {
        List<BillingsDTO> list = billingsService.getAllBillings();
        return ResponseEntity.ok(list);
    }
    @Operation(summary = "Get Billing By ID", description = "Retrieve one billing record by ID")
    @ApiResponses({
            @ApiResponse(responseCode = BillingConstants.STATUS_200, description = BillingConstants.MESSAGE_200),
            @ApiResponse(responseCode = BillingConstants.STATUS_404, description = BillingConstants.MESSAGE_404)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BillingsDTO> getBillingById(@PathVariable Long id) {
        BillingsDTO dto = billingsService.getBillingById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Update Billing Record", description = "Update an existing billing record")
    @ApiResponses({
            @ApiResponse(responseCode = BillingConstants.STATUS_200, description = BillingConstants.MESSAGE_200),
            @ApiResponse(responseCode = BillingConstants.STATUS_404, description = BillingConstants.MESSAGE_404),
            @ApiResponse(responseCode = BillingConstants.STATUS_417, description = BillingConstants.MESSAGE_417_UPDATE)
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateBillings(@Valid @RequestBody BillingsDTO dto) {
        billingsService.updateBillings(dto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(BillingConstants.STATUS_200, BillingConstants.MESSAGE_200));
    }

    @Operation(summary = "Delete Billing Record", description = "Delete a billing record by ID")
    @ApiResponses({
            @ApiResponse(responseCode = BillingConstants.STATUS_200, description = BillingConstants.MESSAGE_200),
            @ApiResponse(responseCode = BillingConstants.STATUS_404, description = BillingConstants.MESSAGE_404),
            @ApiResponse(responseCode = BillingConstants.STATUS_417, description = BillingConstants.MESSAGE_417_DELETE)
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteBillings(@PathVariable Long id) {
        billingsService.deleteBillings(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(BillingConstants.STATUS_200, BillingConstants.MESSAGE_200));
    }

    @Operation(summary = "Get Billings by Appointment ID", description = "Retrieve all billing records for a specific appointment")
    @ApiResponses({
            @ApiResponse(responseCode = BillingConstants.STATUS_200, description = BillingConstants.MESSAGE_200),
            @ApiResponse(responseCode = BillingConstants.STATUS_404, description = BillingConstants.MESSAGE_404)
    })
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<BillingsDTO>> getBillingsByAppointmentId(@PathVariable("appointmentId") Long appointmentId) {
        List<BillingsDTO> list = billingsService.getBillingsByAppointmentId(appointmentId);
        return ResponseEntity.ok(list);
    }

}

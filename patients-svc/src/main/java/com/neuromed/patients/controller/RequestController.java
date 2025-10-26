package com.neuromed.patients.controller;

import com.neuromed.patients.constants.RequestsConstants;
import com.neuromed.patients.dto.ErrorResponseDto;
import com.neuromed.patients.dto.RequestDTO;
import com.neuromed.patients.dto.ResponseDto;
import com.neuromed.patients.dto.TransferDetailsDTO;
import com.neuromed.patients.exception.ResourceNotFoundException;
import com.neuromed.patients.service.IRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Request Management",
        description = "CRUD and Transfer REST APIs for managing patient requests"
)
@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {

    private final IRequestService requestService;

    // ✅ CREATE Request
    @Operation(summary = "Create New Request", description = "Adds a new request for a patient")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Request created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "417", description = "Request creation failed")
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createRequest(@RequestBody RequestDTO dto) {
        RequestDTO created = requestService.createRequest(dto);
        if (created != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto(RequestsConstants.STATUS_201, RequestsConstants.MESSAGE_201_CREATE));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(RequestsConstants.STATUS_417, RequestsConstants.MESSAGE_417_CREATE));
    }

    // ✅ LIST Requests by Patient ID
    @Operation(summary = "List Requests by Patient ID", description = "Retrieves all requests made by a specific patient")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Requests list fetched successfully"),
            @ApiResponse(responseCode = "404", description = "No requests found for this patient",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/list/{patientId}")
    public ResponseEntity<?> listRequests(@PathVariable Long patientId) {
        List<RequestDTO> requests = requestService.getRequests(patientId);
        if (requests.isEmpty()) {
            throw new ResourceNotFoundException("Request", "patientId", String.valueOf(patientId));
        }
        return ResponseEntity.status(HttpStatus.OK).body(requests);
    }

    // ✅ GET Request Details by ID
    @Operation(summary = "Fetch Request Details", description = "Fetches detailed information about a specific request")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request details fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Request not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<RequestDTO> getRequestDetails(@PathVariable Long id) {
        RequestDTO request = requestService.getRequestDetails(id);
        if (request == null) {
            throw new ResourceNotFoundException("Request", "id", String.valueOf(id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(request);
    }

    @Operation(summary = "Update Request", description = "Updates an existing patient request")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request updated successfully"),
            @ApiResponse(responseCode = "404", description = "Request not found"),
            @ApiResponse(responseCode = "417", description = "Update operation failed")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateRequest(@PathVariable Long id, @RequestBody RequestDTO dto) {
        RequestDTO updated = requestService.updateRequest(id, dto);
        if (updated != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(RequestsConstants.STATUS_200, RequestsConstants.MESSAGE_200_UPDATE));
        }
        throw new ResourceNotFoundException("Request", "id", String.valueOf(id));
    }

    // ✅ DELETE Request
    @Operation(summary = "Delete Request", description = "Deletes a patient request by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Request not found"),
            @ApiResponse(responseCode = "417", description = "Delete operation failed")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteRequest(@PathVariable Long id) {
        boolean deleted = requestService.deleteRequest(id);
        if (deleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(RequestsConstants.STATUS_200, RequestsConstants.MESSAGE_200_DELETE));
        }
        throw new ResourceNotFoundException("Request", "id", String.valueOf(id));
    }
    
// ✅ List All Transfer Requests
    @Operation(summary = "List All Transfer Requests", description = "Retrieves all transfer requests")
    @GetMapping("/transfers")
    public ResponseEntity<List<RequestDTO>> getAllTransfers() {
        List<RequestDTO> transfers = requestService.getAllTransfers();
        if (transfers.isEmpty()) {
            throw new ResourceNotFoundException("Request", "requestType", "PATIENT_TRANSFER");
        }
        return ResponseEntity.ok(transfers);
    }

    // ✅ Get Transfer Request Details
    @Operation(summary = "Get Transfer Request Details", description = "Fetches full transfer details using Feign Clients")
    @GetMapping("/transfers/{transferId}")
    public ResponseEntity<TransferDetailsDTO> getTransferDetails(@PathVariable String transferId) {
        TransferDetailsDTO details = requestService.getTransferDetails(transferId);
        return ResponseEntity.ok(details);
    }
    

    // ✅ Approve Transfer Request
    @Operation(summary = "Approve Transfer Request", description = "Approve a transfer request by ID")
    @PutMapping("/transfers/{transferId}/approve")
    public ResponseEntity<ResponseDto> approveRequest(@PathVariable String transferId) {
        requestService.approveRequest(transferId);
        return ResponseEntity.ok(new ResponseDto(RequestsConstants.STATUS_200, "Transfer request approved successfully"));
    }

    // ✅ Reject Transfer Request
    @Operation(summary = "Reject Transfer Request", description = "Reject a transfer request by ID")
    @PutMapping("/transfers/{id}/reject")
    public ResponseEntity<ResponseDto> rejectRequest(@PathVariable Long id, @RequestBody RequestDTO dto) {
        requestService.rejectRequest(id, dto.getReason());
        return ResponseEntity.ok(new ResponseDto(RequestsConstants.STATUS_200, "Transfer request rejected successfully"));
    }
}
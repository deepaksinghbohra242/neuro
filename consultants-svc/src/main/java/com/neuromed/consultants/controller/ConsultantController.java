package com.neuromed.consultants.controller;

import com.neuromed.consultants.constants.ConsultantConstants;
import com.neuromed.consultants.dto.*;
import com.neuromed.consultants.service.IConsultantService;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Collections;

@Tag(name = "CRUD REST APIs for Consultants", description = "CRUD REST APIs to CREATE, UPDATE, FETCH, AND DELETE consultant details")
@RestController
@RequestMapping("/api")
@Validated
public class ConsultantController {

	private final IConsultantService consultantService;

	@Autowired
	public ConsultantController(IConsultantService consultantService,
			ConsultantsContactInfoDto consultantsContactInfoDto) {
		this.consultantService = consultantService;
	}

	@Operation(summary = "Create Consultant", description = "Create a new consultant")
	@ApiResponses({
			@ApiResponse(responseCode = ConsultantConstants.STATUS_201, description = ConsultantConstants.MESSAGE_201),
			@ApiResponse(responseCode = ConsultantConstants.STATUS_500, description = ConsultantConstants.MESSAGE_500)
	})
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createConsultant(@Valid @RequestBody ConsultantDto consultantDto) {
		consultantService.createConsultant(consultantDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(ConsultantConstants.STATUS_201, ConsultantConstants.MESSAGE_201));
	}

	@Operation(summary = "Fetch Consultant by ID", description = "Fetch consultant details by ID")
	@ApiResponses({
			@ApiResponse(responseCode = ConsultantConstants.STATUS_200, description = ConsultantConstants.MESSAGE_200),
			@ApiResponse(responseCode = ConsultantConstants.STATUS_500, description = ConsultantConstants.MESSAGE_500)
	})
	@GetMapping("/fetch/{consultantId}")
	public ResponseEntity<ConsultantDto> fetchConsultant(@PathVariable Long consultantId) {
		ConsultantDto consultantDto = consultantService.fetchConsultant(consultantId);
		return ResponseEntity.ok(consultantDto);
	}

	@Operation(summary = "List Consultants", description = "Retrieve all consultants from the system with sorting support")
	@ApiResponses({
			@ApiResponse(responseCode = ConsultantConstants.STATUS_200, description = ConsultantConstants.MESSAGE_200),
			@ApiResponse(responseCode = ConsultantConstants.STATUS_500, description = ConsultantConstants.MESSAGE_500)
	})
	@GetMapping("/list")
	public ResponseEntity<List<ConsultantDetailsDto>> listConsultants(
			@RequestHeader("neuromed-correlation-id") String correlationId,
			@RequestParam(name = "sortBy",defaultValue = "experienceYears") String sortBy,
			@RequestParam(name = "sortDir",defaultValue = "desc") String sortDir) {

		List<ConsultantDetailsDto> consultants = consultantService.listConsultants(sortBy, sortDir , correlationId);
		return ResponseEntity.ok(consultants);
	}

	@Operation(summary = "Fetch Available Consultants by Appointment Type and Date", description = "Retrieves consultants available for a specific appointment type and date.")
	@ApiResponses({
			@ApiResponse(responseCode = ConsultantConstants.STATUS_200, description = ConsultantConstants.MESSAGE_200),
			@ApiResponse(responseCode = "404", description = "No consultants found for this criteria", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
			@ApiResponse(responseCode = ConsultantConstants.STATUS_500, description = ConsultantConstants.MESSAGE_500)
	})
	@GetMapping("/availableConsultants")
	public ResponseEntity<List<ConsultantDto>> getAvailableConsultants(
			@RequestParam String visitType,
			@RequestParam @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

		List<ConsultantDto> availableConsultants = consultantService.findAvailableConsultants(visitType,
				date);

		if (availableConsultants.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
		}
		return ResponseEntity.ok(availableConsultants);
	}

	@Operation(summary = "Update Consultant", description = "Update consultant details")
	@ApiResponses({
			@ApiResponse(responseCode = ConsultantConstants.STATUS_200, description = ConsultantConstants.MESSAGE_200),
			@ApiResponse(responseCode = ConsultantConstants.STATUS_417, description = ConsultantConstants.MESSAGE_417_UPDATE),
			@ApiResponse(responseCode = ConsultantConstants.STATUS_500, description = ConsultantConstants.MESSAGE_500)
	})
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateConsultant(@Valid @RequestBody ConsultantDto consultantDto) {
		boolean isUpdated = consultantService.updateConsultant(consultantDto);
		if (isUpdated) {
			return ResponseEntity.ok(new ResponseDto(ConsultantConstants.STATUS_200,
					ConsultantConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(ConsultantConstants.STATUS_417,
							ConsultantConstants.MESSAGE_417_UPDATE));
		}
	}

	@Operation(summary = "Delete Consultant", description = "Delete consultant by ID")
	@ApiResponses({
			@ApiResponse(responseCode = ConsultantConstants.STATUS_200, description = ConsultantConstants.MESSAGE_200),
			@ApiResponse(responseCode = ConsultantConstants.STATUS_417, description = ConsultantConstants.MESSAGE_417_DELETE),
			@ApiResponse(responseCode = ConsultantConstants.STATUS_500, description = ConsultantConstants.MESSAGE_500)
	})
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteConsultant(@RequestParam Long consultantId) {
		boolean isDeleted = consultantService.deleteConsultant(consultantId);
		if (isDeleted) {
			return ResponseEntity.ok(new ResponseDto(ConsultantConstants.STATUS_200,
					ConsultantConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(ConsultantConstants.STATUS_417,
							ConsultantConstants.MESSAGE_417_DELETE));
		}
	}

	@Operation(summary = "Fetch Consultant Details", description = "Fetch Consultant details using consultant ID and correlation ID")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
	})
	@GetMapping("/fetchConsultantDetails")
	public ResponseEntity<ConsultantDetailsDto> fetchConsultantDetails(
			@RequestHeader("neuromed-correlation-id") String correlationId,
			@RequestParam("consultantId") Long consultantId) {
		ConsultantDetailsDto detailsDto = consultantService.fetchConsultantDetails(consultantId, correlationId);
		return ResponseEntity.ok(detailsDto);
	}
}

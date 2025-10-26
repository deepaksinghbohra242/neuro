package com.neuromed.reports.controller;

import com.neuromed.reports.dto.ResponseDto;
import com.neuromed.reports.dto.TestsDTO;
import com.neuromed.reports.service.ITestsService;
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

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Tests",
        description = "REST APIs to CREATE, UPDATE, FETCH AND DELETE test records"
)
@RestController
@RequestMapping("/api/tests")
@Validated
public class TestsController {

    private final ITestsService testsService;

    @Autowired
    public TestsController(ITestsService testsService) {
        this.testsService = testsService;
    }

    @Operation(summary = "Create Test", description = "Create a new test")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Test created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/createTests")
    public ResponseEntity<ResponseDto> createTest(@Valid @RequestBody TestsDTO testsDTO) {
        testsService.createTest(testsDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Test created successfully"));
    }

    @Operation(summary = "Fetch Test", description = "Fetch test by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Test fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Test not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/fetchTests")
    public ResponseEntity<TestsDTO> fetchTest(@RequestParam Long testId) {
        TestsDTO testDTO = testsService.fetchTest(testId);
        return ResponseEntity.ok(testDTO);
    }

    @Operation(summary = "Update Test", description = "Update test details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Test updated successfully"),
            @ApiResponse(responseCode = "417", description = "Update failed"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/updateTests")
    public ResponseEntity<ResponseDto> updateTest(@Valid @RequestBody TestsDTO testsDTO) {
        boolean isUpdated = testsService.updateTest(testsDTO);
        if (isUpdated) {
            return ResponseEntity.ok(new ResponseDto("200", "Test updated successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto("417", "Test update failed"));
        }
    }

    @Operation(summary = "Delete Test", description = "Delete test by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Test deleted successfully"),
            @ApiResponse(responseCode = "417", description = "Delete failed"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/deleteTests")
    public ResponseEntity<ResponseDto> deleteTest(@RequestParam Long testId) {
        boolean isDeleted = testsService.deleteTest(testId);
        if (isDeleted) {
            return ResponseEntity.ok(new ResponseDto("200", "Test deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto("417", "Test delete failed"));
        }
    }
    @Operation(
            summary = "Get All Tests",
            description = "Retrieve all test records from the database"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tests retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/allTests")
    public ResponseEntity<List<TestsDTO>> getAllTests() {
        List<TestsDTO> testsList = testsService.getAllTests();
        return ResponseEntity.ok(testsList);
    }

    @Operation(summary = "Get Tests by Appointment ID", description = "Retrieve all tests for a specific appointment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tests retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No tests found for the appointment"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/byAppointment")
    public ResponseEntity<List<TestsDTO>> getTestsByAppointment(@RequestParam("appointmentId") Long appointmentId) {
        List<TestsDTO> tests = testsService.getTestsByAppointmentId(appointmentId);
        if (tests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tests);
        }
        return ResponseEntity.ok(tests);
    }

}

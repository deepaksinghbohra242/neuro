package com.neuromed.billing.controller;

import com.neuromed.billing.constants.BillingsConstants;
import com.neuromed.billing.dto.BillingHistoryDTO;
import com.neuromed.billing.service.IBillingHistoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;  
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Billing Management",
        description = "APIs for managing billing and payment history"
)
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BillingHistoryController {

    private final IBillingHistoryService billingService;

    @GetMapping("/history/{patientId}")
    @Operation(summary = "Get Billing History by Patient ID")
    public ResponseEntity<?> getBillingHistory(@PathVariable Long patientId) {
        try {
            List<BillingHistoryDTO> billings = billingService.getBillingHistoryByPatientId(patientId);
            if (billings.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(BillingsConstants.MESSAGE_200_FETCH + " (No records found)");
            }
            return ResponseEntity.ok(billings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(BillingsConstants.MESSAGE_417_FETCH);
        }
    }
}

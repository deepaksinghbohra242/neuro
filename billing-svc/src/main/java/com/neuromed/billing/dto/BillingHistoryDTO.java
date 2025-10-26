package com.neuromed.billing.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingHistoryDTO {
    private Long id;
    private Long patientId;
    private Long appointmentId;
    private LocalDate dueDate;
    private String description;
    private BigDecimal amount;
    private String status;;
}

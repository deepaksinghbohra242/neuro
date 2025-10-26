package com.neuromed.billing.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Schema(
        name = "BillingErrorResponse",
        description = "Schema to hold error response information for billing APIs"
)
public class ErrorResponseDto {

    @Schema(
            description = "API path invoked by client",
            example = "/api/billings?id=123"
    )
    private String apiPath;

    @Schema(
            description = "HTTP status code representing the error",
            example = "404"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message describing the error",
            example = "Billing record not found"
    )
    private String errorMessage;

    @Schema(
            description = "Timestamp representing when the error happened",
            example = "2025-10-11T13:45:00"
    )
    private LocalDateTime errorTime;
}

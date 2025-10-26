package com.neuromed.reports.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(name = "ErrorResponseDto", description = "Schema to hold error response information")
public class ErrorResponseDto {

        @Schema(description = "API path invoked by client")
        private String apiPath;

        @Schema(description = "HTTP status code representing the error")
        private HttpStatus errorCode;

        @Schema(description = "Error message describing what went wrong")
        private String errorMessage;

        @Schema(description = "Timestamp when the error occurred")
        private LocalDateTime errorTime;
}
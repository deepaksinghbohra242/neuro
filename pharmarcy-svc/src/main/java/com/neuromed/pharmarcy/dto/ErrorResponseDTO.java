package com.neuromed.pharmarcy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Schema to hold error response information")
public class ErrorResponseDTO {

        @Schema(description = "API path invoked by client")
        private String apiPath;

        @Schema(description = "Error code representing the error that occurred")
        private HttpStatus errorCode;

        @Schema(description = "Error message describing the issue")
        private String errorMessage;

        @Schema(description = "Timestamp indicating when the error occurred")
        private LocalDateTime errorTime;
}

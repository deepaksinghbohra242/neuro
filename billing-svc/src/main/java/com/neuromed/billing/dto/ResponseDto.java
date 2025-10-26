package com.neuromed.billing.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "BillingResponse",
        description = "Schema to hold successful response information for billing APIs"
)
public class ResponseDto {

    @Schema(
            description = "Status code in the response",
            example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Status message in the response",
            example = "Billing record processed successfully"
    )
    private String statusMsg;
}

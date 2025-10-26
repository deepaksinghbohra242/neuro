package com.neuromed.reports.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class ReportDTO {
    private Long id;
    private String report_type;
    @Schema(
            description = "Duration for which the report is valid or relevant",
            example = "2024-06-01T10:15:30"
    )
    private LocalDateTime duration;
}

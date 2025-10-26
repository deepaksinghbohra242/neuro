package com.neuromed.consultants.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {

    private Long id;
    private Long consultantId;
    private LocalDate startDate;
    @Schema(type = "string", example = "14:30:00", description = "Time slot in HH:mm:ss format")
    private LocalTime startTime ;
    private LocalDate endDate;
    @Schema(type = "string", example = "14:30:00", description = "Time slot in HH:mm:ss format")
    private  LocalTime endTime;
    private String reasonForUnavailability;
    private String status;

}

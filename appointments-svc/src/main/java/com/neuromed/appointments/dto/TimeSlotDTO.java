package com.neuromed.appointments.dto;

import lombok.Data;
import java.time.LocalTime;

@Data
public class TimeSlotDTO {
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean available;
}

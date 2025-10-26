package com.neuromed.appointments.dto;

import lombok.Data;
import java.util.List;

@Data
public class ScheduleDTO {
    private Long id;
    private Long consultantId;
    private String date;
    private List<TimeSlotDTO> timeSlots;
}

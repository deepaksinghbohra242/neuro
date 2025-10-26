package com.neuromed.appointments.service;

import com.neuromed.appointments.dto.ScheduleDTO;
import java.util.List;

public interface ITimeSlotService {
    ScheduleDTO createSchedule(ScheduleDTO dto);
    ScheduleDTO getSchedule(Long id);
    List<ScheduleDTO> getSchedules();
    void updateSlotAvailability(Long slotId, boolean available);
}

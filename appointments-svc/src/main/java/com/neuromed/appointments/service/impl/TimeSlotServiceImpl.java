package com.neuromed.appointments.service.impl;

import com.neuromed.appointments.dto.*;
import com.neuromed.appointments.entity.*;
import com.neuromed.appointments.repository.*;
import com.neuromed.appointments.service.ITimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeSlotServiceImpl implements ITimeSlotService {

    private final ScheduleRepository scheduleRepository;
    private final TimeSlotRepository timeSlotRepository;

    @Override
    public ScheduleDTO createSchedule(ScheduleDTO dto) {
        Schedule schedule = new Schedule();
        schedule.setConsultantId(dto.getConsultantId());
        schedule.setDate(dto.getDate());

        List<TimeSlot> timeSlots = dto.getTimeSlots().stream().map(tsDto -> {
            TimeSlot slot = new TimeSlot();
            slot.setStartTime(tsDto.getStartTime());
            slot.setEndTime(tsDto.getEndTime());
            slot.setAvailable(tsDto.isAvailable());
            slot.setSchedule(schedule);
            return slot;
        }).collect(Collectors.toList());

        schedule.setTimeSlots(timeSlots);
        Schedule saved = scheduleRepository.save(schedule);

        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public ScheduleDTO getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setConsultantId(schedule.getConsultantId());
        dto.setDate(schedule.getDate());
        dto.setTimeSlots(schedule.getTimeSlots().stream().map(ts -> {
            TimeSlotDTO t = new TimeSlotDTO();
            t.setId(ts.getId());
            t.setStartTime(ts.getStartTime());
            t.setEndTime(ts.getEndTime());
            t.setAvailable(ts.isAvailable());
            return t;
        }).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public List<ScheduleDTO> getSchedules() {
        return scheduleRepository.findAll().stream()
                .map(s -> getSchedule(s.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void updateSlotAvailability(Long slotId, boolean available) {
        TimeSlot slot = timeSlotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Time slot not found"));
        slot.setAvailable(available);
        timeSlotRepository.save(slot);
    }
}

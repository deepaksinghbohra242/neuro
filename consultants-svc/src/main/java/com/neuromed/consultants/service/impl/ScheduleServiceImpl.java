package com.neuromed.consultants.service.impl;

import com.neuromed.consultants.dto.ScheduleDto;
import com.neuromed.consultants.entity.Schedule;
import com.neuromed.consultants.exception.ResourceNotFoundException;
import com.neuromed.consultants.mapper.ScheduleMapper;
import com.neuromed.consultants.repository.ScheduleRepository;
import com.neuromed.consultants.service.IScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements IScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public void createSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = ScheduleMapper.mapToSchedule(scheduleDto);
        scheduleRepository.save(schedule);
    }

    @Override
    public ScheduleDto fetchSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "id", scheduleId.toString()));
        return ScheduleMapper.mapToScheduleDto(schedule);
    }

    @Override
    public boolean updateSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleRepository.findById(scheduleDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "id", scheduleDto.getId().toString()));

        // Update fields
        schedule.setConsultantId(scheduleDto.getConsultantId());
        schedule.setStartDate(scheduleDto.getStartDate());
        schedule.setStartTime(scheduleDto.getStartTime());
        schedule.setEndDate(scheduleDto.getEndDate());
        schedule.setEndTime(scheduleDto.getEndTime());
        schedule.setReasonForUnavailability(scheduleDto.getReasonForUnavailability());
        if (scheduleDto.getStatus() != null) {
            schedule.setStatus(Schedule.Status.valueOf(scheduleDto.getStatus()));
        }

        scheduleRepository.save(schedule);
        return true;
    }

    @Override
    public boolean deleteSchedule(Long scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new ResourceNotFoundException("Schedule", "id", scheduleId.toString());
        }
        scheduleRepository.deleteById(scheduleId);
        return true;
    }

    @Override
    public ScheduleDto fetchScheduleDetails(Long scheduleId, String correlationId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "id", scheduleId.toString()));
        // Additional logic (e.g. logging, correlationId tracking) can go here
        return ScheduleMapper.mapToScheduleDto(schedule);
    }
}

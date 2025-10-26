package com.neuromed.consultants.mapper;

import com.neuromed.consultants.dto.ConsultantDto;
import com.neuromed.consultants.dto.ScheduleDto;
import com.neuromed.consultants.entity.Consultant;
import com.neuromed.consultants.entity.Schedule;

public class ScheduleMapper {

    public static ScheduleDto mapToScheduleDto(Schedule schedule) {
        if (schedule == null) return null;

        ScheduleDto dto = new ScheduleDto();
        dto.setId(schedule.getId());
        dto.setConsultantId(schedule.getConsultantId());
        dto.setStartDate(schedule.getStartDate());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndDate(schedule.getEndDate());
        dto.setEndTime(schedule.getEndTime());
        dto.setReasonForUnavailability(schedule.getReasonForUnavailability());
        dto.setStatus(schedule.getStatus() != null ? schedule.getStatus().name() : null);
        return dto;
    }

    public static Schedule mapToSchedule(ScheduleDto dto) {
        if (dto == null) return null;

        Schedule schedule = new Schedule();
        schedule.setId(dto.getId());
        schedule.setConsultantId(dto.getConsultantId());
        schedule.setStartDate(dto.getStartDate());
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndDate(dto.getEndDate());
        schedule.setEndTime(dto.getEndTime());
        schedule.setReasonForUnavailability(dto.getReasonForUnavailability());

        if (dto.getStatus() != null) {
            schedule.setStatus(Schedule.Status.valueOf(dto.getStatus()));
        }

        return schedule;
    }

}

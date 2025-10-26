package com.neuromed.appointments.mapper;

import com.neuromed.appointments.dto.AppointmentDTO;
import com.neuromed.appointments.entity.Appointment;

public class AppointmentMapper {

    public static AppointmentDTO mapToAppointmentDto(Appointment appointment, AppointmentDTO dto) {
        dto.setId(appointment.getId());
        dto.setPatientId(appointment.getPatientId());
        dto.setConsultantId(appointment.getConsultantId());
        dto.setDate(appointment.getDate());
        dto.setEndTime(appointment.getEndTime());
        dto.setTimeSlot(appointment.getTimeSlot());
        dto.setStatus(appointment.getStatus().name());
        dto.setVisitType(appointment.getVisitType());
        dto.setReason(appointment.getReason());
        dto.setBillingId(appointment.getBillingId());
        dto.setDuration(appointment.getDuration());
        return dto;
    }

    public static Appointment mapToAppointment(AppointmentDTO dto, Appointment appointment) {
        appointment.setPatientId(dto.getPatientId());
        appointment.setConsultantId(dto.getConsultantId());
        appointment.setDate(dto.getDate());
        appointment.setTimeSlot(dto.getTimeSlot());
        appointment.setEndTime(dto.getEndTime());
        appointment.setStatus(Appointment.Status.valueOf(dto.getStatus()));
        appointment.setVisitType(dto.getVisitType());
        appointment.setReason(dto.getReason());
        appointment.setBillingId(dto.getBillingId());
        return appointment;
    }
}

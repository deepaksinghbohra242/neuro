package com.neuromed.appointments.mapper;

import com.neuromed.appointments.dto.AppointmentMedicalDetailsDTO;
import com.neuromed.appointments.entity.AppointmentMedicalDetails;

public class AppointmentMedicalMapper {

    public static AppointmentMedicalDetailsDTO toDto(AppointmentMedicalDetails entity) {
        if (entity == null) return null;

        AppointmentMedicalDetailsDTO dto = new AppointmentMedicalDetailsDTO();
        dto.setId(entity.getId());
        dto.setAppointmentId(entity.getAppointmentId());
        dto.setWeight(entity.getWeight());
        dto.setBloodPressure(entity.getBloodPressure());
        dto.setHeartRate(entity.getHeartRate());
        dto.setDate(entity.getDate());
        dto.setDoctorName(entity.getDoctorName());
        dto.setClinicName(entity.getClinicName());
        dto.setAddress(entity.getAddress());
        return dto;
    }

    public static AppointmentMedicalDetails toEntity(AppointmentMedicalDetailsDTO dto) {
        if (dto == null) return null;

        AppointmentMedicalDetails entity = new AppointmentMedicalDetails();
        entity.setId(dto.getId());
        entity.setAppointmentId(dto.getAppointmentId());
        entity.setWeight(dto.getWeight());
        entity.setBloodPressure(dto.getBloodPressure());
        entity.setHeartRate(dto.getHeartRate());
        entity.setDate(dto.getDate());
        entity.setDoctorName(dto.getDoctorName());
        entity.setClinicName(dto.getClinicName());
        entity.setAddress(dto.getAddress());
        return entity;
    }
}





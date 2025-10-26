package com.neuromed.appointments.mapper;

import java.util.ArrayList;

import com.neuromed.appointments.dto.AppointmentOtherDetailsDTO;
import com.neuromed.appointments.entity.AppointmentOtherDetails;
import java.util.Arrays;

public class AppointmentOtherDetailsMapper {

    public static AppointmentOtherDetailsDTO toDto(AppointmentOtherDetails entity) {
        return AppointmentOtherDetailsDTO.builder()
                .id(entity.getId())
                .appointmentId(entity.getAppointmentId())
                .fullName(entity.getFullName())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .relation(entity.getRelation())
                .insuranceCompany(entity.getInsuranceCompany())
                .policyNumber(entity.getPolicyNumber())
                .documents(entity.getDocuments() != null
                        ? Arrays.asList(entity.getDocuments().split(","))
                        : new ArrayList<>()) 
                .build();
    }

    public static AppointmentOtherDetails toEntity(AppointmentOtherDetailsDTO dto) {
        return AppointmentOtherDetails.builder()
                .id(dto.getId())
                .appointmentId(dto.getAppointmentId())
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .relation(dto.getRelation())
                .insuranceCompany(dto.getInsuranceCompany())
                .policyNumber(dto.getPolicyNumber())
                .documents(dto.getDocuments() != null
                    ? String.join(",", dto.getDocuments())
                    : null)
                .build();
    }
}

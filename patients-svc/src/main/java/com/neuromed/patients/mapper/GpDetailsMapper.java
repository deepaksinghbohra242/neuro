package com.neuromed.patients.mapper;

import com.neuromed.patients.dto.GpDetailsDTO;
import com.neuromed.patients.entity.GpDetails;

public class GpDetailsMapper {

    public static GpDetailsDTO toDTO(GpDetails entity) {
        if (entity == null) return null;

        return GpDetailsDTO.builder()
                .id(entity.getId())
                .profileId(entity.getProfileId())
                .doctorName(entity.getDoctorName())
                .clinicName(entity.getClinicName())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .licenceNumber(entity.getLicenceNumber())
                .patientSince(entity.getPatientSince())
                .address(entity.getAddress())
                .city(entity.getCity())
                .state(entity.getState())
                .country(entity.getCountry())
                .zipCode(entity.getZipCode())
                .build();
    }

    public static GpDetails toEntity(GpDetailsDTO dto) {
        if (dto == null) return null;

        return GpDetails.builder()
                .id(dto.getId())
                .profileId(dto.getProfileId())
                .doctorName(dto.getDoctorName())
                .clinicName(dto.getClinicName())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .licenceNumber(dto.getLicenceNumber())
                .patientSince(dto.getPatientSince())
                .address(dto.getAddress())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .zipCode(dto.getZipCode())
                .build();
    }
}

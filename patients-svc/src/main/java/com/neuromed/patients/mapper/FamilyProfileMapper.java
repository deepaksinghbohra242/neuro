package com.neuromed.patients.mapper;

import com.neuromed.patients.dto.FamilyProfileDTO;
import com.neuromed.patients.entity.FamilyProfile;

public class FamilyProfileMapper {

    public static FamilyProfileDTO mapToDTO(FamilyProfile entity) {
        if (entity == null) return null;
        return FamilyProfileDTO.builder()
                .id(entity.getId())
                .patientId(entity.getPatientId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .relation(entity.getRelation())
                .email(entity.getEmail())
                .email(entity.getEmail())
                .build();
    }

    public static FamilyProfile mapToEntity(FamilyProfileDTO dto) {
        if (dto == null) return null;
        return FamilyProfile.builder()
                .id(dto.getId())
                .patientId(dto.getPatientId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .relation(dto.getRelation())
                .age(dto.getAge())
                .email(dto.getEmail())
                .build();
    }
}

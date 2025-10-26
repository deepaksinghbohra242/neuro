package com.neuromed.patients.mapper;

import com.neuromed.patients.dto.MedicalDetailsDTO;
import com.neuromed.patients.entity.MedicalDetails;

public class MedicalDetailsMapper {

    public static MedicalDetailsDTO toDTO(MedicalDetails entity) {
        return MedicalDetailsDTO.builder()
                .id(entity.getId())
                .profileId(entity.getProfileId())
                .bloodGroup(entity.getBloodGroup())
                .creyOSScore(entity.getCreyOSScore())
                .physicallyChallenged(entity.getPhysicallyChallenged())
                .weight(entity.getWeight())
                .bloodPressure(entity.getBloodPressure())
                .heartRate(entity.getHeartRate())
                .otherMedicalCondition(entity.getOtherMedicalCondition())
                .allergies(entity.getAllergies())
                .smoking(entity.getSmoking())
                .drinking(entity.getDrinking())
                .build();
    }

    public static MedicalDetails toEntity(MedicalDetailsDTO dto) {
        return MedicalDetails.builder()
                .id(dto.getId())
                .profileId(dto.getProfileId())
                .bloodGroup(dto.getBloodGroup())
                .creyOSScore(dto.getCreyOSScore())
                .physicallyChallenged(dto.getPhysicallyChallenged())
                .weight(dto.getWeight())
                .bloodPressure(dto.getBloodPressure())
                .heartRate(dto.getHeartRate())
                .otherMedicalCondition(dto.getOtherMedicalCondition())
                .allergies(dto.getAllergies())
                .smoking(dto.getSmoking())
                .drinking(dto.getDrinking())
                .build();
    }
}

package com.neuromed.consultants.mapper;

import com.neuromed.consultants.entity.Consultant;
import com.neuromed.consultants.dto.ConsultantDto;

public class ConsultantMapper {

    public static ConsultantDto mapToConsultantDto(Consultant consultant) {
        if (consultant == null) return null;
        ConsultantDto dto = new ConsultantDto();
        dto.setId(consultant.getId());
        dto.setUserId(consultant.getUserId());
        dto.setSpecialization(consultant.getSpecialization());
        dto.setExperienceYears(consultant.getExperienceYears());
        dto.setQualification(consultant.getQualification());
        dto.setStatus(consultant.getStatus() != null ? consultant.getStatus().name() : null);
        return dto;
    }

    public static Consultant mapToConsultant(ConsultantDto dto) {
        if (dto == null) return null;
        Consultant consultant = new Consultant();
        consultant.setId(dto.getId());
        consultant.setUserId(dto.getUserId());
        consultant.setSpecialization(dto.getSpecialization());
        consultant.setExperienceYears(dto.getExperienceYears());
        consultant.setQualification(dto.getQualification());
        if (dto.getStatus() != null) {
            consultant.setStatus(Consultant.Status.valueOf(dto.getStatus()));
        }
        return consultant;
    }
}

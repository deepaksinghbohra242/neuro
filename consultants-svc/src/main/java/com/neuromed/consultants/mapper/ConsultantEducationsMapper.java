package com.neuromed.consultants.mapper;

import com.neuromed.consultants.dto.ConsultantEducationsDTO;
import com.neuromed.consultants.entity.ConsultantEducations;

public class ConsultantEducationsMapper {

    public static ConsultantEducationsDTO mapToConsultantEducationDTO(ConsultantEducations consultantEducations) {
        if (consultantEducations == null) return null;
        ConsultantEducationsDTO dto   = new ConsultantEducationsDTO();
        dto.setId(consultantEducations.getId());
        dto.setConsultantId(consultantEducations.getConsultantId());
        dto.setDegreeName(consultantEducations.getDegreeName());
        dto.setFieldOfStudy(consultantEducations.getFieldOfStudy());
        dto.setInstitution(consultantEducations.getInstitution());
        dto.setStartYear(consultantEducations.getStartYear());
        dto.setEndYear(consultantEducations.getEndYear());
        return dto;
    }


    public static ConsultantEducations mapToConsultantEducation(ConsultantEducationsDTO dto) {
        if (dto == null) return null;
        ConsultantEducations  consultantEducations = new ConsultantEducations();
        consultantEducations.setId(dto.getId());
        consultantEducations.setConsultantId(dto.getConsultantId());
        consultantEducations.setDegreeName(dto.getDegreeName());
        consultantEducations.setFieldOfStudy(dto.getFieldOfStudy());
        consultantEducations.setInstitution(dto.getInstitution());
        consultantEducations.setStartYear(dto.getStartYear());
        consultantEducations.setEndYear(dto.getEndYear());
        return consultantEducations;
    }
}

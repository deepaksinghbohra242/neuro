package com.neuromed.consultants.mapper;

import com.neuromed.consultants.dto.ConsultantCertificationsDTO;
import com.neuromed.consultants.entity.ConsultantCertifications;

public class ConsultantCertificationsMapper {
    public static ConsultantCertificationsDTO mapToConsultantCertificationsDTO(ConsultantCertifications certifications) {
        if (certifications == null) return null;
        ConsultantCertificationsDTO dto = new ConsultantCertificationsDTO();
        dto.setId(certifications.getId());
        dto.setConsultantId(certifications.getConsultantId());
        dto.setUrl(certifications.getUrl());
        return dto;
    }

    public static ConsultantCertifications mapToConsultantCertifications(ConsultantCertificationsDTO dto) {
        if (dto == null) return null;
        ConsultantCertifications consultantCertifications = new ConsultantCertifications();
        consultantCertifications.setId(dto.getId());
        consultantCertifications.setConsultantId(dto.getConsultantId());
        consultantCertifications.setUrl(dto.getUrl());
        return consultantCertifications;
    }
}

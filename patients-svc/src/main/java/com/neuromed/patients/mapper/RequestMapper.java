package com.neuromed.patients.mapper;

import com.neuromed.patients.dto.RequestDTO;
import com.neuromed.patients.entity.Request;

public class RequestMapper {

    public static RequestDTO toDTO(Request entity) {
        if (entity == null) return null;

        return RequestDTO.builder()
                .id(entity.getId())
                .transferId(entity.getTransferId())
                .date(entity.getDate())
                .consultantId(entity.getConsultantId())
                .requestType(entity.getRequestType() != null ? entity.getRequestType().name().toLowerCase() : null)
                .doctorName(entity.getDoctorName())
                .description(entity.getDescription())
                .patientId(entity.getPatientId())
                .status(entity.getStatus() != null ? entity.getStatus().name().toLowerCase() : null)
                .reason(entity.getReason())
                .duration(entity.getDuration())
                .transferredTo(entity.getTransferredTo())
                .build();
    }

    public static Request toEntity(RequestDTO dto) {
        if (dto == null) return null;

        Request request = new Request();
        request.setId(dto.getId());
        request.setTransferId(dto.getTransferId());
        request.setDate(dto.getDate());
        request.setConsultantId(dto.getConsultantId());
        request.setDoctorName(dto.getDoctorName());
        request.setDescription(dto.getDescription());
        request.setPatientId(dto.getPatientId());
        request.setReason(dto.getReason());
        request.setDuration(dto.getDuration());
        request.setTransferredTo(dto.getTransferredTo());

        if (dto.getRequestType() != null) {
            request.setRequestType(Request.RequestType.valueOf(dto.getRequestType().toUpperCase().replace(" ", "_")));
        }

        if (dto.getStatus() != null) {
            request.setStatus(Request.Status.valueOf(dto.getStatus().toUpperCase()));
        }

        return request;
    }
}

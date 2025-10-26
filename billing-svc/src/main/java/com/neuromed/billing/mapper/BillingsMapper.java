package com.neuromed.billing.mapper;

import com.neuromed.billing.dto.BillingsDTO;
import com.neuromed.billing.entity.Billings;

public class BillingsMapper {
    public static BillingsDTO mapToBillingsDto(Billings billings, BillingsDTO dto) {
        dto.setId(billings.getId());
        dto.setPatientId(billings.getPatientId());
        dto.setAppointmentId(billings.getAppointmentId());
        dto.setDueDate(billings.getDueDate());
        dto.setDescription(billings.getDescription());
        dto.setAmount(billings.getAmount());
        dto.setStatus(String.valueOf(billings.getStatus()));
        return dto;
    }


    public static Billings mapToBillings(BillingsDTO dto, Billings billings) {
        billings.setPatientId(dto.getPatientId());
        billings.setAppointmentId(dto.getAppointmentId());
        billings.setDueDate(dto.getDueDate());
        billings.setDescription(dto.getDescription());
        billings.setAmount(dto.getAmount());
        billings.setStatus(Billings.Status.valueOf(dto.getStatus()));
        return billings;
    }
}

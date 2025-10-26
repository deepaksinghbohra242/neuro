package com.neuromed.billing.mapper;

import com.neuromed.billing.dto.BillingHistoryDTO;
import com.neuromed.billing.entity.BillingHistory;

public class BillingHistoryMapper {

    // Map Entity -> DTO
    public static BillingHistoryDTO mapToBillingHistoryDto(BillingHistory billingHistory, BillingHistoryDTO dto) {
        dto.setId(billingHistory.getId());
        dto.setPatientId(billingHistory.getPatientId());
        dto.setAppointmentId(billingHistory.getAppointmentId());
        dto.setDueDate(billingHistory.getDueDate());
        dto.setDescription(billingHistory.getDescription());
        dto.setAmount(billingHistory.getAmount());
        dto.setStatus(billingHistory.getStatus().name());
        return dto;
    }

    // Map DTO -> Entity
    public static BillingHistory mapToBillingHistory(BillingHistoryDTO dto, BillingHistory billingHistory) {
        billingHistory.setPatientId(dto.getPatientId());
        billingHistory.setAppointmentId(dto.getAppointmentId());
        billingHistory.setDueDate(dto.getDueDate());
        billingHistory.setDescription(dto.getDescription());
        billingHistory.setAmount(dto.getAmount());
        billingHistory.setStatus(BillingHistory.Status.valueOf(dto.getStatus().toUpperCase()));
        return billingHistory;
    }
}

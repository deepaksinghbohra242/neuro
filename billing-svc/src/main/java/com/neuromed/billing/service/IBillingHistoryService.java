package com.neuromed.billing.service;

import com.neuromed.billing.dto.BillingHistoryDTO;
import java.util.List;

public interface IBillingHistoryService {
    List<BillingHistoryDTO> getBillingHistoryByPatientId(Long patientId);
}

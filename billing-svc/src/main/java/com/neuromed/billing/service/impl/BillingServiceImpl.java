package com.neuromed.billing.service.impl;

import com.neuromed.billing.dto.BillingHistoryDTO;
import com.neuromed.billing.entity.BillingHistory;
import com.neuromed.billing.mapper.BillingHistoryMapper;
import com.neuromed.billing.repository.BillingHistoryRepository;
import com.neuromed.billing.service.IBillingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements IBillingHistoryService {

    private final BillingHistoryRepository billingHistoryRepository;

    @Override
    public List<BillingHistoryDTO> getBillingHistoryByPatientId(Long patientId) {
        List<BillingHistory> histories = billingHistoryRepository.findByPatientId(patientId);

        return histories.stream()
                .map(b -> BillingHistoryMapper.mapToBillingHistoryDto(b, new BillingHistoryDTO()))
                .collect(Collectors.toList());
    }
}

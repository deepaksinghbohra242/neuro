package com.neuromed.billing.repository;

import com.neuromed.billing.entity.BillingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BillingHistoryRepository extends JpaRepository<BillingHistory, Long> {
    List<BillingHistory> findByPatientId(Long patientId);
}

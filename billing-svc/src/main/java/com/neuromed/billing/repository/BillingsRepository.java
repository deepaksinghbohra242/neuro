package com.neuromed.billing.repository;

import com.neuromed.billing.entity.Billings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingsRepository extends JpaRepository<Billings,Long> {
    List<Billings> findByAppointmentId(Long appointmentId);
}

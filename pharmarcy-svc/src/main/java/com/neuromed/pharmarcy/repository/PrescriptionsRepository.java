package com.neuromed.pharmarcy.repository;


import com.neuromed.pharmarcy.dto.PrescriptionDTO;
import com.neuromed.pharmarcy.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.Collection;

public interface PrescriptionsRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByStatus(Prescription.Status prescriptionStatus);
}

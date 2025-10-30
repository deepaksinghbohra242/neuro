package com.neuromed.pharmarcy.repository;

import com.neuromed.pharmarcy.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
    List<Prescription> findByStatus(Prescription.Status status);
}

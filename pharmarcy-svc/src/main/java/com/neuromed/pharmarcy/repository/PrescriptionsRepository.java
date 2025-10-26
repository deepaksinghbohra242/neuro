package com.neuromed.pharmarcy.repository;


import com.neuromed.pharmarcy.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionsRepository extends JpaRepository<Prescription, Long> {
}

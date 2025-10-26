package com.neuromed.patients.repository;

import com.neuromed.patients.entity.MedicalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalDetailsRepository extends JpaRepository<MedicalDetails, Long> {
}

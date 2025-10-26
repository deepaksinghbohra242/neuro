package com.neuromed.patients.repository;

import com.neuromed.patients.entity.FamilyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyProfileRepository extends JpaRepository<FamilyProfile, Long> {
    List<FamilyProfile> findByPatientId(Long patientId);
}


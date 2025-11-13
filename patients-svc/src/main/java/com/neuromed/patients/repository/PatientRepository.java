package com.neuromed.patients.repository;

import com.neuromed.patients.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByStatus(Patient.Status status);
}

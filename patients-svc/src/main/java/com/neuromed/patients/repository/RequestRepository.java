package com.neuromed.patients.repository;

import com.neuromed.patients.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByPatientId(Long patientId);
    @Query("SELECT r FROM Request r WHERE r.requestType = 'PATIENT_TRANSFER'") 
    List<Request> findAllTransfers(); 
    Optional<Request> findByTransferId(String transferId);
}

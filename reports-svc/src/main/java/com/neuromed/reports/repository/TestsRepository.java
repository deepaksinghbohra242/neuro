package com.neuromed.reports.repository;

import com.neuromed.reports.entity.Tests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestsRepository extends JpaRepository<Tests, Long> {
    List<Tests> findByAppointmentId(Long appointmentId);
}

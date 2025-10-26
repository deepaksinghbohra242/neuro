package com.neuromed.reports.repository;

import com.neuromed.reports.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {
    List<Test> findByAppointmentId(Long appointmentId);
}

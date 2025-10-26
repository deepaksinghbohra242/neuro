package com.neuromed.reports.repository;

import com.neuromed.reports.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}

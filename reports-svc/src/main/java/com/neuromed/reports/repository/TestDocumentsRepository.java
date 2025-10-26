package com.neuromed.reports.repository;

import com.neuromed.reports.entity.TestDocuments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDocumentsRepository extends JpaRepository<TestDocuments,Long> {
}

package com.neuromed.consultants.repository;

import com.neuromed.consultants.entity.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long> {
  List<Consultant> findByStatus(Consultant.Status status);
}
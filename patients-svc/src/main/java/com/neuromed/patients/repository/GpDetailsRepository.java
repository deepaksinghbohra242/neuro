package com.neuromed.patients.repository;

import com.neuromed.patients.entity.GpDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GpDetailsRepository extends JpaRepository<GpDetails, Long> {
    Optional<GpDetails> findByProfileId(Long profileId);
}

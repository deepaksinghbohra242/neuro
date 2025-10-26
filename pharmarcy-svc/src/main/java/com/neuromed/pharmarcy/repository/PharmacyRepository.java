package com.neuromed.pharmarcy.repository;

import com.neuromed.pharmarcy.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}

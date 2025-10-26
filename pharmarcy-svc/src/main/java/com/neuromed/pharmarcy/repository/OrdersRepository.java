package com.neuromed.pharmarcy.repository;

import com.neuromed.pharmarcy.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByPrescriptionId(Long prescriptionId);
}

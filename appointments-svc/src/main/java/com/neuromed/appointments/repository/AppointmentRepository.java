package com.neuromed.appointments.repository;

import com.neuromed.appointments.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("Select a from Appointment a where a.consultantId = :consultantId")
    List<Appointment> findByConsultantId(@Param("consultantId") Long consultantId);
}

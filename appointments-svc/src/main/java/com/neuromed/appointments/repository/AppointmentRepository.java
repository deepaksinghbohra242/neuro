package com.neuromed.appointments.repository;

import com.neuromed.appointments.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // ✅ Existing method: fetch appointments by consultant
    @Query("SELECT a FROM Appointment a WHERE a.consultantId = :consultantId")
    List<Appointment> findByConsultantId(@Param("consultantId") Long consultantId);


    @Query("SELECT a FROM Appointment a WHERE a.createdAt BETWEEN :startOfDay AND :endOfDay")
    List<Appointment> findAppointmentsCreatedToday(
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );
}

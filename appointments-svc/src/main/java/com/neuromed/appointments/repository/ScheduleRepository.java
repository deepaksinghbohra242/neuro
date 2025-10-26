package com.neuromed.appointments.repository;

import com.neuromed.appointments.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByConsultantIdAndDate(Long consultantId, String date);
}

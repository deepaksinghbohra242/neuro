package com.neuromed.appointments.repository;

import com.neuromed.appointments.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByScheduleId(Long scheduleId);
}

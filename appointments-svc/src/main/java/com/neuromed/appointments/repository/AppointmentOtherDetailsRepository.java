package com.neuromed.appointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.neuromed.appointments.entity.AppointmentOtherDetails;

@Repository
public interface AppointmentOtherDetailsRepository extends JpaRepository<AppointmentOtherDetails, Long> {
}

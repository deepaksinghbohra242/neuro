package com.neuromed.appointments.repository;

import com.neuromed.appointments.entity.AppointmentAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentAddressRepository extends JpaRepository<AppointmentAddress, Long> {
    List<AppointmentAddress> findByAppointmentId(Long appointmentId);
}

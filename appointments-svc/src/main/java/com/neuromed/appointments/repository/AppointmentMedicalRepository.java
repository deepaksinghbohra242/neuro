package com.neuromed.appointments.repository;
import com.neuromed.appointments.entity.AppointmentMedicalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentMedicalRepository extends JpaRepository<AppointmentMedicalDetails,Long> {
}

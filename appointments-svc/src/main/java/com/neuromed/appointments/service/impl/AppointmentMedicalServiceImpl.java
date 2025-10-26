package com.neuromed.appointments.service.impl;
import com.neuromed.appointments.dto.AppointmentMedicalDetailsDTO;
import com.neuromed.appointments.entity.AppointmentMedicalDetails;
import com.neuromed.appointments.exception.ResourceNotFoundException;
import com.neuromed.appointments.mapper.AppointmentMedicalMapper;
import com.neuromed.appointments.repository.AppointmentMedicalRepository;
import com.neuromed.appointments.service.IAppointmentMedicalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentMedicalServiceImpl implements IAppointmentMedicalService {

    private final AppointmentMedicalRepository repository;

    @Override
    public void createAppointmentMedicalDetails(AppointmentMedicalDetailsDTO dto) {
        AppointmentMedicalDetails entity = AppointmentMedicalMapper.toEntity(dto);
        repository.save(entity);
    }

    @Override
    public AppointmentMedicalDetailsDTO fetchAppointmentMedicalDetails(Long id) {
        AppointmentMedicalDetails entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AppointmentMedicalDetails", "id", id.toString()));
        return AppointmentMedicalMapper.toDto(entity);
    }

    @Override
    public boolean updateAppointmentMedicalDetails(AppointmentMedicalDetailsDTO dto) {
        AppointmentMedicalDetails existing = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("AppointmentMedicalDetails", "id", dto.getId().toString()));

        // Update fields from DTO
        existing.setAppointmentId(dto.getAppointmentId());
        existing.setWeight(dto.getWeight());
        existing.setBloodPressure(dto.getBloodPressure());
        existing.setHeartRate(dto.getHeartRate());
        existing.setDate(dto.getDate());
        existing.setDoctorName(dto.getDoctorName());
        existing.setClinicName(dto.getClinicName());
        existing.setAddress(dto.getAddress());

        repository.save(existing);
        return true;
    }

    @Override
    public boolean deleteAppointmentMedicalDetails(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public AppointmentMedicalDetailsDTO fetchAppointmentMedicalDetails(Long id, String correlationId) {
        // If correlationId is used for tracing/logging, handle it here
        return fetchAppointmentMedicalDetails(id);
    }
}


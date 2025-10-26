package com.neuromed.appointments.service.impl;

import com.neuromed.appointments.dto.AppointmemtsAddressDTO;
import com.neuromed.appointments.entity.AppointmentAddress;
import com.neuromed.appointments.exception.ResourceNotFoundException;
import com.neuromed.appointments.mapper.AppointmentAddressMapper;
import com.neuromed.appointments.repository.AppointmentAddressRepository;
import com.neuromed.appointments.service.IAppointmentAddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentAddressServiceImpl implements IAppointmentAddressService {

    private final AppointmentAddressRepository appointmentAddressRepository;

    @Override
    public void createAppointmentAddress(AppointmemtsAddressDTO addressDto) {
        AppointmentAddress address = AppointmentAddressMapper.mapToAppointmentAddress(addressDto);
        appointmentAddressRepository.save(address);
    }

    @Override
    public AppointmemtsAddressDTO fetchAppointmentAddress(Long addressId) {
        AppointmentAddress address = appointmentAddressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("AppointmentAddress", "id", addressId.toString()));
        return AppointmentAddressMapper.mapToAppointmentAddressDto(address);
    }

    @Override
    public boolean updateAppointmentAddress(AppointmemtsAddressDTO addressDto) {
        AppointmentAddress existing = appointmentAddressRepository.findById(addressDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("AppointmentAddress", "id", addressDto.getId().toString()));

        // Update fields
        AppointmentAddress updated = AppointmentAddressMapper.mapToAppointmentAddress(addressDto);
        updated.setId(existing.getId()); // Ensure ID remains consistent

        appointmentAddressRepository.save(updated);
        return true;
    }

    @Override
    public boolean deleteAppointmentAddress(Long addressId) {
        if (!appointmentAddressRepository.existsById(addressId)) {
            throw new ResourceNotFoundException("AppointmentAddress", "id", addressId.toString());
        }
        appointmentAddressRepository.deleteById(addressId);
        return true;
    }
}

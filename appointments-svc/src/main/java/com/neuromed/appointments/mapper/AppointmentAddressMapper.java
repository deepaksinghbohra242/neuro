package com.neuromed.appointments.mapper;

import com.neuromed.appointments.dto.AppointmemtsAddressDTO;
import com.neuromed.appointments.entity.AppointmentAddress;

public class AppointmentAddressMapper {

    // Convert Entity to DTO
    public static AppointmemtsAddressDTO mapToAppointmentAddressDto(AppointmentAddress address) {
        if (address == null) return null;

        AppointmemtsAddressDTO dto = new AppointmemtsAddressDTO();
        dto.setId(address.getId());
        dto.setAppointmentId(address.getAppointmentId());
        dto.setFullAddress(address.getFullAddress());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setCountry(address.getCountry());
        dto.setZipCode(address.getZipCode());
        return dto;
    }

    // Convert DTO to Entity
    public static AppointmentAddress mapToAppointmentAddress(AppointmemtsAddressDTO dto) {
        if (dto == null) return null;

        AppointmentAddress address = new AppointmentAddress();
        address.setId(dto.getId());
        address.setAppointmentId(dto.getAppointmentId());
        address.setFullAddress(dto.getFullAddress());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setCountry(dto.getCountry());
        address.setZipCode(dto.getZipCode());
        return address;
    }
}

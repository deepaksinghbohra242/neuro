package com.neuromed.appointments.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.neuromed.appointments.dto.AppointmentOtherDetailsDTO;

public interface IAppointmentOtherDetailsService {
    AppointmentOtherDetailsDTO create(AppointmentOtherDetailsDTO dto);
    AppointmentOtherDetailsDTO getById(Long id);
    List<AppointmentOtherDetailsDTO> listAll();
    AppointmentOtherDetailsDTO update(Long id, AppointmentOtherDetailsDTO dto);
    void delete(Long id);
    AppointmentOtherDetailsDTO uploadDocuments(Long id, List<MultipartFile> files);
}

package com.neuromed.patients.service;

import com.neuromed.patients.dto.MedicalDetailsDTO;
import java.util.List;

public interface IMedicalDetailsService {
    MedicalDetailsDTO createMedicalDetails(MedicalDetailsDTO dto);
    MedicalDetailsDTO getMedicalDetailsById(Long id);
    List<MedicalDetailsDTO> getAllMedicalDetails();
    MedicalDetailsDTO updateMedicalDetails(Long id, MedicalDetailsDTO dto);
    boolean deleteMedicalDetails(Long id);
}

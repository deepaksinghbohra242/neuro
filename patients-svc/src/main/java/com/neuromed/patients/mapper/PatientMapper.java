package com.neuromed.patients.mapper;

import com.neuromed.patients.dto.PatientDTO;
import com.neuromed.patients.entity.Patient;

public class PatientMapper {

     public static PatientDTO mapToPatientDTO(Patient patient) {
        if (patient == null) return null;
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setUserId(patient.getUserId());
        dto.setPhoneNumber(patient.getPhoneNumber());
        dto.setDateOfBirth(patient.getDateOfBirth());
        dto.setOccupation(patient.getOccupation());
        dto.setNationality(patient.getNationality());
        dto.setPpsn(patient.getPpsn());
        dto.setAddressLine1(patient.getAddressLine1());
        dto.setCity(patient.getCity());
        dto.setState(patient.getState());
        dto.setCountry(patient.getCountry());
        dto.setZipCode(patient.getZipCode());
        dto.setStatus(patient.getStatus() != null ? patient.getStatus().name().toLowerCase() : null);
        return dto;
    }

    public static Patient mapToPatient(PatientDTO dto) {
        if (dto == null) return null;
        Patient patient = new Patient();
        patient.setId(dto.getId());
        patient.setUserId(dto.getUserId());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setOccupation(dto.getOccupation());
        patient.setNationality(dto.getNationality());
        patient.setPpsn(dto.getPpsn());
        patient.setAddressLine1(dto.getAddressLine1());
        patient.setCity(dto.getCity());
        patient.setState(dto.getState());
        patient.setCountry(dto.getCountry());
        patient.setZipCode(dto.getZipCode());
        if (dto.getStatus() != null) {
            patient.setStatus(Patient.Status.valueOf(dto.getStatus().toUpperCase()));
        }
        return patient;
    }
}

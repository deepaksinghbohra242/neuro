package com.neuromed.patients.service.impl;

import com.neuromed.patients.dto.PatientDTO;
import com.neuromed.patients.dto.PatientDetailsDTO;
import com.neuromed.patients.dto.UserModel;
import com.neuromed.patients.entity.Patient;
import com.neuromed.patients.exception.ResourceNotFoundException;
import com.neuromed.patients.mapper.PatientMapper;
import com.neuromed.patients.repository.PatientRepository;
import com.neuromed.patients.service.IPatientService;
import com.neuromed.patients.service.client.UserFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements IPatientService {

    private final PatientRepository patientRepository;
    private UserFeignClient userFeignClient;

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        logger.info("createPatient called for userId={}", patientDTO != null ? patientDTO.getUserId() : "null");
        Patient patient = PatientMapper.mapToPatient(patientDTO);
        Patient saved = patientRepository.save(patient);
        logger.info("Patient created successfully id={} userId={}", saved.getId(), saved.getUserId());
        return PatientMapper.mapToPatientDTO(saved);
    }

    @Override
    public List<PatientDTO> getPatients() {
        logger.info("getPatients called");
        List<PatientDTO> list = patientRepository.findAll()
                .stream()
                .map(PatientMapper::mapToPatientDTO)
                .collect(Collectors.toList());
        logger.debug("getPatients returning {} patients", list != null ? list.size() : 0);
        return list;
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        logger.info("updatePatient called for id={}", id);
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id.toString()));

        existing.setUserId(patientDTO.getUserId());
        existing.setPhoneNumber(patientDTO.getPhoneNumber());
        existing.setDateOfBirth(patientDTO.getDateOfBirth());
        existing.setOccupation(patientDTO.getOccupation());
        existing.setNationality(patientDTO.getNationality());
        existing.setPpsn(patientDTO.getPpsn());
        existing.setAddressLine1(patientDTO.getAddressLine1());
        existing.setCity(patientDTO.getCity());
        existing.setState(patientDTO.getState());
        existing.setCountry(patientDTO.getCountry());
        existing.setZipCode(patientDTO.getZipCode());

        if (patientDTO.getStatus() != null) {
            existing.setStatus(Patient.Status.valueOf(patientDTO.getStatus().toUpperCase()));
        }

        Patient updated = patientRepository.save(existing);
        logger.info("updatePatient successful id={}", updated.getId());
        return PatientMapper.mapToPatientDTO(updated);
    }

    @Override
    public boolean deletePatient(Long id) {
        logger.info("deletePatient called for id={}", id);
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            logger.info("deletePatient succeeded for id={}", id);
            return true;
        }
        logger.warn("deletePatient no-op, not found id={}", id);
        return false;
    }

    @Override
    public PatientDetailsDTO fetchPatient(Long patientId, String correlationId) {
        logger.info("fetchPatient called for patientId={} correlationId={}", patientId, correlationId);
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", String.valueOf(patientId)));
        ResponseEntity<UserModel> userModelResponseEntity = userFeignClient.fetchUserDetails(correlationId, patient.getUserId().toString());
        logger.info("fetchPatient fetched patientId={} user={}", patientId, userModelResponseEntity.getBody() != null ? userModelResponseEntity.getBody().getId() : "null");
        PatientDetailsDTO patientDetailsDTO = getPatientDetailsDTO(patient, userModelResponseEntity);
        logger.debug("fetchPatient returning details for patientId={}", patientId);
        return patientDetailsDTO;
    }

    private static PatientDetailsDTO getPatientDetailsDTO(Patient patient, ResponseEntity<UserModel> userModelResponseEntity) {
        PatientDetailsDTO patientDetailsDTO = new PatientDetailsDTO();
        patientDetailsDTO.setId(patient.getId());
        patientDetailsDTO.setPhoneNumber(patient.getPhoneNumber());
        patientDetailsDTO.setDateOfBirth(patient.getDateOfBirth());
        patientDetailsDTO.setOccupation(patient.getOccupation());
        patientDetailsDTO.setNationality(patient.getNationality());
        patientDetailsDTO.setPpsn(patient.getPpsn());
        patientDetailsDTO.setAddressLine1(patient.getAddressLine1());
        patientDetailsDTO.setCity(patient.getCity());
        patientDetailsDTO.setState(patient.getState());
        patientDetailsDTO.setCountry(patient.getCountry());
        patientDetailsDTO.setZipCode(patient.getZipCode());
        if (patient.getStatus() != null) {
            patientDetailsDTO.setStatus(patient.getStatus().name());
        }
        patientDetailsDTO.setUserModel(userModelResponseEntity != null ? userModelResponseEntity.getBody() : null);
        return patientDetailsDTO;
    }
}

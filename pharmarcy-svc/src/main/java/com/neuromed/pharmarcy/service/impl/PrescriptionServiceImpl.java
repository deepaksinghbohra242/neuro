package com.neuromed.pharmarcy.service.impl;

import com.neuromed.pharmarcy.dto.*;

import com.neuromed.pharmarcy.entity.Prescription;
import com.neuromed.pharmarcy.execption.ResourceNotFoundException;
import com.neuromed.pharmarcy.mapper.PrescriptionsMapper;
import com.neuromed.pharmarcy.repository.PrescriptionsRepository;
import com.neuromed.pharmarcy.service.IPrescriptionMedicineService;
import com.neuromed.pharmarcy.service.IPrescriptionService;
import com.neuromed.pharmarcy.service.client.ConsultantsFeignClient;
import com.neuromed.pharmarcy.service.client.PatientFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PrescriptionServiceImpl implements IPrescriptionService {

  private final PrescriptionsRepository prescriptionsRepository;
  private final IPrescriptionMedicineService iPrescriptionMedicineService;
  private final ConsultantsFeignClient consultantsFeignClient;
  private final PatientFeignClient patientFeignClient;


  @Override
  public PrescriptionDTO createPrescription(PrescriptionDTO prescriptionDTO) {
    Prescription entity = PrescriptionsMapper.mapToPrescriptionsEntity(prescriptionDTO);
    Prescription saved = prescriptionsRepository.save(entity);
    return PrescriptionsMapper.mapToPrescriptionsDTO(saved);
  }

  @Override
  public List<PrescriptionDTO> getAllPrescriptions() {
    return prescriptionsRepository.findAll()
            .stream()
            .map(prescription -> {
              PrescriptionDTO dto = PrescriptionsMapper.mapToPrescriptionsDTO(prescription);
              return dto;
            })
            .collect(Collectors.toList());
  }

  @Override
  public PrescriptionDTO updatePrescription(Long id, PrescriptionDTO prescriptionDTO) {
    Prescription existing = prescriptionsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prescription", "id", id.toString()));

    existing.setPatientId(prescriptionDTO.getPatientId());
    existing.setConsultantId(prescriptionDTO.getConsultantId());
    existing.setPharmacyId(prescriptionDTO.getPharmacyId());
    existing.setDate(prescriptionDTO.getDate());
    existing.setNoOfMedicines(prescriptionDTO.getNoOfMedicines());
    existing.setDuration(prescriptionDTO.getDuration());

    Prescription updatedEntity = PrescriptionsMapper.mapToPrescriptionsEntity(prescriptionDTO);
    existing.setPreferredService(updatedEntity.getPreferredService());

    Prescription updated = prescriptionsRepository.save(existing);

    PrescriptionDTO dto = PrescriptionsMapper.mapToPrescriptionsDTO(updated);
    return dto;
  }


  @Override
  public boolean deletePrescription(Long id) {
    if (prescriptionsRepository.existsById(id)) {
      prescriptionsRepository.deleteById(id);
      return true;
    }
    return false;
  }

  @Override
  public PrescriptionDTO getPrescriptionById(Long id) {
    Prescription entity = prescriptionsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prescription", "id", id.toString()));
    PrescriptionDTO dto = PrescriptionsMapper.mapToPrescriptionsDTO(entity);
    return dto;
  }


  @Override
  public PrescriptionDetailDTO getPrescriptionDetailById(Long id , String correlationId) {
    Prescription detail = prescriptionsRepository.findById(id).orElse(null);
    if (detail == null) return null;

    PrescriptionDetailDTO dto = new PrescriptionDetailDTO();
    dto.setId(detail.getId());
    dto.setPatientId(detail.getPatientId());
    dto.setConsultantId(detail.getConsultantId());
    dto.setPharmacyId(detail.getPharmacyId());
    dto.setDate(detail.getDate());
    dto.setNoOfMedicines(detail.getNoOfMedicines());
    dto.setDuration(detail.getDuration());
    dto.setPreferredService(detail.getPreferredService() != null ? detail.getPreferredService().name() : null);
    dto.setMedicines(iPrescriptionMedicineService.getMedicinesByPrescriptionId(detail.getId()));

    try {
      ResponseEntity<ConsultantDetailsDto> consultantResponse =
              consultantsFeignClient.fetchConsultantDetails(correlationId, detail.getConsultantId());
      if (consultantResponse.getBody() != null) {
        dto.setConsultantUserModel(consultantResponse.getBody().getUserModel());
      }
    } catch (Exception e) {
      System.err.println("Consultant details not found: " + e.getMessage());
    }

    try {
      ResponseEntity<PatientDetailsDTO> patientResponse =
              patientFeignClient.fetchPatientDetails(correlationId, detail.getPatientId());
      if (patientResponse.getBody() != null) {
        dto.setPatientUserModel(patientResponse.getBody().getUserModel());
      }
    } catch (Exception e) {
      System.err.println("Patient details not found: " + e.getMessage());
    }
    return dto;
  }


  @Override
  public List<PrescriptionDTO> listPrescriptions(String correlationId) {
    return prescriptionsRepository.findAll()
            .stream()
            .map(prescription -> {
              PrescriptionDTO dto = PrescriptionsMapper.mapToPrescriptionsDTO(prescription);
              ResponseEntity<ConsultantDetailsDto> consultantResponse = consultantsFeignClient.fetchConsultantDetails(
                      correlationId, prescription.getConsultantId());
              ConsultantDetailsDto consultant = consultantResponse.getBody();
              System.out.println("Consultant fetched: " + consultant);
              if (consultant != null) {
                dto.setConsultantUserModel(consultant.getUserModel());
              }
              return dto;
            })
            .collect(Collectors.toList());
  }


  @Override
  public PrescriptionDTO reorderPrescription(ReorderRequestDTO dto) {
    // what logic we want to build here?
    // does it need to reset pharmacy Id?
    /*Prescriptions existing = prescriptionsRepository.findById(dto.getPrescriptionId())
            .orElseThrow(() -> new ResourceNotFoundException("Prescription", "id", dto.getPrescriptionId().toString()));*/
  return null;
  }

  @Override
  public List<PrescriptionDTO> listPrescriptionsByStatus(String correlationId, String status) {
    try {
      Prescription.Status prescriptionStatus = Prescription.Status.valueOf(status.trim().toUpperCase());

      return prescriptionsRepository.findByStatus(prescriptionStatus)
              .stream()
              .map(prescription -> {
                PrescriptionDTO dto = PrescriptionsMapper.mapToPrescriptionsDTO(prescription);

                try {
                  ResponseEntity<ConsultantDetailsDto> consultantResponse =
                          consultantsFeignClient.fetchConsultantDetails(correlationId, prescription.getConsultantId());
                  ConsultantDetailsDto consultant = consultantResponse.getBody();
                  if (consultant != null) {
                    dto.setConsultantUserModel(consultant.getUserModel());
                  }
                } catch (Exception e) {
                  System.err.println("Failed to fetch consultant details for consultantId={} : {}" +  prescription.getConsultantId() + e.getMessage());
                }

                try {
                  ResponseEntity<PatientDetailsDTO> patientResponse =
                          patientFeignClient.fetchPatientDetails(correlationId, prescription.getPatientId());
                  PatientDetailsDTO patient = patientResponse.getBody();
                  if (patient != null) {
                    dto.setPatientUserModel(patient.getUserModel());
                  }
                } catch (Exception e) {
                  System.err.println("Failed to fetch patient details for patientId={} : {}"+ prescription.getPatientId()+ e.getMessage());
                }

                return dto;
              })
              .collect(Collectors.toList());

    } catch (IllegalArgumentException e) {
      System.err.println("Invalid status provided: {} " + status);
      throw new RuntimeException("Invalid prescription status: " + status);
    }
  }

}

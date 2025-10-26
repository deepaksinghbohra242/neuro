package com.neuromed.consultants.service.impl;

import com.neuromed.consultants.dto.ConsultantCertificationsDTO;
import com.neuromed.consultants.entity.ConsultantCertifications;
import com.neuromed.consultants.exception.ResourceNotFoundException;
import com.neuromed.consultants.mapper.ConsultantCertificationsMapper;
import com.neuromed.consultants.repository.ConsultantCertificationsRepository;
import com.neuromed.consultants.service.IConsultantCertificationsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class ConsultantCertificationsServiceImpl implements IConsultantCertificationsService {

        private ConsultantCertificationsRepository certificationsRepository;


        public ConsultantCertificationsDTO createConsultantCertifications(ConsultantCertificationsDTO certificationsDTO) {
            ConsultantCertifications consultantCertifications = ConsultantCertificationsMapper.mapToConsultantCertifications(certificationsDTO);
            ConsultantCertifications certifications = certificationsRepository.save(consultantCertifications);
            return ConsultantCertificationsMapper.mapToConsultantCertificationsDTO(certifications);
        }

        @Override
        public List<ConsultantCertificationsDTO> getConsultantCertifications() {
            return certificationsRepository.findAll()
                    .stream()
                    .map(ConsultantCertificationsMapper::mapToConsultantCertificationsDTO)
                    .collect(Collectors.toList());
        }

        public   ConsultantCertificationsDTO updateConsultantCertifications(Long id,  ConsultantCertificationsDTO certificationsDTO) {
            ConsultantCertifications certifications = certificationsRepository.findById(id).orElse(null);
            if (certifications != null) {
                certifications.setId(certificationsDTO.getId());
                certifications.setConsultantId(certificationsDTO.getConsultantId());
                certifications.setUrl(certificationsDTO.getUrl());
                ConsultantCertifications certifications1 = certificationsRepository.save(certifications);
                return ConsultantCertificationsMapper.mapToConsultantCertificationsDTO(certifications1);
            }
            return null;
        }

        @Override
        public boolean deleteConsultantCertifications(Long id) {
            if (certificationsRepository.existsById(id)) {
                certificationsRepository.deleteById(id);
                return true;
            }
            return false;
        }

        @Override
        public ConsultantCertificationsDTO fetchConsultantCertifications(Long id, String correlationId) {
            ConsultantCertifications certifications = certificationsRepository.findById(Long.valueOf(id))
                    .orElseThrow(() -> new ResourceNotFoundException("Certification", "id", correlationId));
            return ConsultantCertificationsMapper.mapToConsultantCertificationsDTO(certifications);
        }
    }



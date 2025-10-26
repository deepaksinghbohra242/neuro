package com.neuromed.consultants.service.impl;

import com.neuromed.consultants.dto.ConsultantEducationsDTO;
import com.neuromed.consultants.entity.ConsultantEducations;
import com.neuromed.consultants.exception.ResourceNotFoundException;
import com.neuromed.consultants.mapper.ConsultantEducationsMapper;
import com.neuromed.consultants.repository.ConsultantEducationsRepository;
import com.neuromed.consultants.service.IConsultantEducationsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConsultantEducationsServiceImpl implements IConsultantEducationsService {

    private final ConsultantEducationsRepository educationsRepository;

    @Override
    public ConsultantEducationsDTO createConsultantEducation(ConsultantEducationsDTO educationDTO) {
        ConsultantEducations education = ConsultantEducationsMapper.mapToConsultantEducation(educationDTO);
        ConsultantEducations saved = educationsRepository.save(education);
        return ConsultantEducationsMapper.mapToConsultantEducationDTO(saved);
    }

    @Override
    public List<ConsultantEducationsDTO> getAllConsultantEducations() {
        return educationsRepository.findAll()
                .stream()
                .map(ConsultantEducationsMapper::mapToConsultantEducationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ConsultantEducationsDTO updateConsultantEducation(Long id, ConsultantEducationsDTO educationDTO) {
        ConsultantEducations existing = educationsRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setConsultantId(educationDTO.getConsultantId());
            existing.setDegreeName(educationDTO.getDegreeName());
            existing.setFieldOfStudy(educationDTO.getFieldOfStudy());
            existing.setInstitution(educationDTO.getInstitution());
            existing.setStartYear(educationDTO.getStartYear());
            existing.setEndYear(educationDTO.getEndYear());

            ConsultantEducations updated = educationsRepository.save(existing);
            return ConsultantEducationsMapper.mapToConsultantEducationDTO(updated);
        }
        return null;
    }

    @Override
    public boolean deleteConsultantEducation(Long id) {
        if (educationsRepository.existsById(id)) {
            educationsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ConsultantEducationsDTO fetchConsultantEducation(Long id, String correlationId) {
        ConsultantEducations education = educationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultant Education", "id", correlationId));
        return ConsultantEducationsMapper.mapToConsultantEducationDTO(education);
    }
}

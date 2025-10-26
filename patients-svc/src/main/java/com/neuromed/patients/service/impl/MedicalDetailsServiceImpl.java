package com.neuromed.patients.service.impl;

import com.neuromed.patients.constants.MedicalConstants;
import com.neuromed.patients.dto.MedicalDetailsDTO;
import com.neuromed.patients.entity.MedicalDetails;
import com.neuromed.patients.exception.ResourceNotFoundException;
import com.neuromed.patients.mapper.MedicalDetailsMapper;
import com.neuromed.patients.repository.MedicalDetailsRepository;
import com.neuromed.patients.service.IMedicalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalDetailsServiceImpl implements IMedicalDetailsService {

    private final MedicalDetailsRepository repository;

    @Override
    public MedicalDetailsDTO createMedicalDetails(MedicalDetailsDTO dto) {
        MedicalDetails entity = MedicalDetailsMapper.toEntity(dto);
        return MedicalDetailsMapper.toDTO(repository.save(entity));
    }

    @Override
    public MedicalDetailsDTO getMedicalDetailsById(Long id) {
        MedicalDetails entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalDetails", "id", String.valueOf(id)));

        return MedicalDetailsMapper.toDTO(entity);
    }

    @Override
    public List<MedicalDetailsDTO> getAllMedicalDetails() {
        return repository.findAll()
                .stream()
                .map(MedicalDetailsMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalDetailsDTO updateMedicalDetails(Long id, MedicalDetailsDTO dto) {
        MedicalDetails existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalDetails", "id", String.valueOf(id)));


        existing.setBloodGroup(dto.getBloodGroup());
        existing.setCreyOSScore(dto.getCreyOSScore());
        existing.setPhysicallyChallenged(dto.getPhysicallyChallenged());
        existing.setWeight(dto.getWeight());
        existing.setBloodPressure(dto.getBloodPressure());
        existing.setHeartRate(dto.getHeartRate());
        existing.setOtherMedicalCondition(dto.getOtherMedicalCondition());
        existing.setAllergies(dto.getAllergies());
        existing.setSmoking(dto.getSmoking());
        existing.setDrinking(dto.getDrinking());

        return MedicalDetailsMapper.toDTO(repository.save(existing));
    }

     @Override
    public boolean deleteMedicalDetails(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}

package com.neuromed.patients.service.impl;

import com.neuromed.patients.dto.GpDetailsDTO;
import com.neuromed.patients.entity.GpDetails;
import com.neuromed.patients.exception.ResourceNotFoundException;
import com.neuromed.patients.mapper.GpDetailsMapper;
import com.neuromed.patients.repository.GpDetailsRepository;
import com.neuromed.patients.service.IGpDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GpDetailsServiceImpl implements IGpDetailsService {

    private final GpDetailsRepository repository;

    @Override
    public GpDetailsDTO createGpDetails(GpDetailsDTO dto) {
        GpDetails entity = GpDetailsMapper.toEntity(dto);
        GpDetails saved = repository.save(entity);
        return GpDetailsMapper.toDTO(saved);
    }

    @Override
    public GpDetailsDTO updateGpDetails(Long id, GpDetailsDTO dto) {
        GpDetails existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GpDetails", "id", String.valueOf(id)));

        existing.setDoctorName(dto.getDoctorName());
        existing.setClinicName(dto.getClinicName());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setEmail(dto.getEmail());
        existing.setLicenceNumber(dto.getLicenceNumber());
        existing.setPatientSince(dto.getPatientSince());
        existing.setAddress(dto.getAddress());
        existing.setCity(dto.getCity());
        existing.setState(dto.getState());
        existing.setCountry(dto.getCountry());
        existing.setZipCode(dto.getZipCode());

        GpDetails updated = repository.save(existing);
        return GpDetailsMapper.toDTO(updated);
    }

    @Override
    public GpDetailsDTO getGpDetails(Long id) {
        return repository.findById(id)
                .map(GpDetailsMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("GpDetails", "id", String.valueOf(id)));
    }

    @Override
    public List<GpDetailsDTO> getAllGpDetails() {
        return repository.findAll().stream()
                .map(GpDetailsMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteGpDetails(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("GpDetails", "id", String.valueOf(id));
        }
        repository.deleteById(id);
        return true;
    }
}

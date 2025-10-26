package com.neuromed.appointments.service.impl;

import com.neuromed.appointments.dto.AppointmentOtherDetailsDTO;
import com.neuromed.appointments.entity.AppointmentOtherDetails;
import com.neuromed.appointments.exception.AppointmentOtherDetailsNotFoundException;
import com.neuromed.appointments.mapper.AppointmentOtherDetailsMapper;
import com.neuromed.appointments.repository.AppointmentOtherDetailsRepository;
import com.neuromed.appointments.service.IAppointmentOtherDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;



@Service
@RequiredArgsConstructor
public class AppointmentOtherDetailsServiceImpl implements IAppointmentOtherDetailsService {

    private final AppointmentOtherDetailsRepository repository;

    @Override
    public AppointmentOtherDetailsDTO create(AppointmentOtherDetailsDTO dto) {
        AppointmentOtherDetails entity = AppointmentOtherDetailsMapper.toEntity(dto);
        return AppointmentOtherDetailsMapper.toDto(repository.save(entity));
    }

    @Override
    public AppointmentOtherDetailsDTO getById(Long id) {
        AppointmentOtherDetails entity = repository.findById(id)
                .orElseThrow(() -> new AppointmentOtherDetailsNotFoundException("Record not found"));
        return AppointmentOtherDetailsMapper.toDto(entity);
    }

    @Override
    public List<AppointmentOtherDetailsDTO> listAll() {
        return repository.findAll().stream()
                .map(AppointmentOtherDetailsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentOtherDetailsDTO update(Long id, AppointmentOtherDetailsDTO dto) {
        AppointmentOtherDetails existing = repository.findById(id)
                .orElseThrow(() -> new AppointmentOtherDetailsNotFoundException("Record not found"));

        existing.setFullName(dto.getFullName());
        existing.setPhone(dto.getPhone());
        existing.setEmail(dto.getEmail());
        existing.setRelation(dto.getRelation());
        existing.setInsuranceCompany(dto.getInsuranceCompany());
        existing.setPolicyNumber(dto.getPolicyNumber());

        return AppointmentOtherDetailsMapper.toDto(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new AppointmentOtherDetailsNotFoundException("Record not found");
        }
        repository.deleteById(id);
    }

    @Override
    public AppointmentOtherDetailsDTO uploadDocuments(Long id, List<MultipartFile> files) {
        AppointmentOtherDetails entity = repository.findById(id)
                .orElseThrow(() -> new AppointmentOtherDetailsNotFoundException("Record not found"));

        List<String> savedPaths = new ArrayList<>();
        Path uploadDir = Paths.get("uploads/appointment-documents/" + id);
        try {
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            for (MultipartFile file : files) {
                Path filePath = uploadDir.resolve(file.getOriginalFilename());
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                savedPaths.add(filePath.toString());
            }

            // Combine old + new files if needed
            List<String> allPaths = new ArrayList<>();
            if (entity.getDocuments() != null && !entity.getDocuments().isEmpty()) {
                allPaths.addAll(Arrays.asList(entity.getDocuments().split(",")));
            }
            allPaths.addAll(savedPaths);

            entity.setDocuments(String.join(",", allPaths));
            repository.save(entity);

            return AppointmentOtherDetailsMapper.toDto(entity);

        } catch (Exception e) {
            throw new RuntimeException("Error uploading files: " + e.getMessage());
        }
    }
  }

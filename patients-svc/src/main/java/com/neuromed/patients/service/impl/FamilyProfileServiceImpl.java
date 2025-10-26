package com.neuromed.patients.service.impl;

import com.neuromed.patients.dto.FamilyProfileDTO;
import com.neuromed.patients.entity.FamilyProfile;
import com.neuromed.patients.repository.FamilyProfileRepository;
import com.neuromed.patients.service.IFamilyProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FamilyProfileServiceImpl implements IFamilyProfileService {

    private final FamilyProfileRepository familyProfileRepository;

    @Override
    public FamilyProfileDTO createFamilyProfile(FamilyProfileDTO dto) {
        FamilyProfile familyProfile = FamilyProfile.builder()
                .patientId(dto.getPatientId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .relation(dto.getRelation())
                .age(dto.getAge())
                .email(dto.getEmail())
                .build();
        FamilyProfile saved = familyProfileRepository.save(familyProfile);

        return FamilyProfileDTO.builder()
                .id(saved.getId())
                .patientId(saved.getPatientId())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .relation(saved.getRelation())
                .age(saved.getAge())
                .email(saved.getEmail())
                .build();
    }

    @Override
    public List<FamilyProfileDTO> getFamilyProfiles(Long patientId) {
        return familyProfileRepository.findByPatientId(patientId)
                .stream()
                .map(fp -> FamilyProfileDTO.builder()
                        .id(fp.getId())
                        .patientId(fp.getPatientId())
                        .firstName(fp.getFirstName())
                        .lastName(fp.getLastName())
                        .relation(fp.getRelation())
                        .age(fp.getAge())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public boolean inviteMember(String email) {
        // TODO: Integrate email sending logic
        return email != null && email.contains("@");
    }
}

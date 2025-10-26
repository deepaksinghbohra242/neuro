package com.neuromed.patients.service;

import com.neuromed.patients.dto.FamilyProfileDTO;
import java.util.List;

public interface IFamilyProfileService {
    FamilyProfileDTO createFamilyProfile(FamilyProfileDTO dto);
    List<FamilyProfileDTO> getFamilyProfiles(Long patientId);
    boolean inviteMember(String email);
}

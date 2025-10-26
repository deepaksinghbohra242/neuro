package com.neuromed.patients.controller;

import com.neuromed.patients.constants.FamilyProfileConstants;
import com.neuromed.patients.dto.FamilyProfileDTO;
import com.neuromed.patients.dto.ResponseDto;
import com.neuromed.patients.exception.ResourceNotFoundException;
import com.neuromed.patients.service.IFamilyProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Family Profile Management", description = "APIs for managing family profiles")
@RestController
@RequestMapping("/api/profile/family")
@RequiredArgsConstructor
public class FamilyProfileController {

    private final IFamilyProfileService familyProfileService;

    @PostMapping("/create")
    @Operation(summary = "Create Family Profile")
    public ResponseEntity<ResponseDto> createFamilyProfile(@RequestBody FamilyProfileDTO dto) {
        FamilyProfileDTO created = familyProfileService.createFamilyProfile(dto);
        if (created != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(FamilyProfileConstants.STATUS_201, FamilyProfileConstants.MESSAGE_201_CREATE));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(FamilyProfileConstants.STATUS_417, FamilyProfileConstants.MESSAGE_417_CREATE));
    }

    @GetMapping("/list/{patientId}")
    @Operation(summary = "List Family Profiles by Patient ID")
    public ResponseEntity<?> listFamilyProfiles(@PathVariable Long patientId) {
        List<FamilyProfileDTO> profiles = familyProfileService.getFamilyProfiles(patientId);
        if (profiles.isEmpty()) {
            return ResponseEntity.ok(new ResponseDto(FamilyProfileConstants.STATUS_200, "No family profiles found"));
        }
        return ResponseEntity.ok(profiles);
    }

    @PostMapping("/invite")
    @Operation(summary = "Invite Family Member by Email")
    public ResponseEntity<ResponseDto> inviteMember(@RequestParam String email) {
        boolean invited = familyProfileService.inviteMember(email);
        if (invited) {
            return ResponseEntity.ok(new ResponseDto(FamilyProfileConstants.STATUS_200, FamilyProfileConstants.MESSAGE_200_INVITE));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(FamilyProfileConstants.STATUS_417, FamilyProfileConstants.MESSAGE_417_INVITE));
    }
}

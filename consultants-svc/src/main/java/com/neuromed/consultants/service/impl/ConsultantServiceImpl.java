package com.neuromed.consultants.service.impl;

import com.neuromed.consultants.dto.ConsultantDetailsDto;
import com.neuromed.consultants.dto.UserModel;
import com.neuromed.consultants.service.client.AppointmentClient;
import com.neuromed.consultants.dto.AppointmentResponseDto;
import com.neuromed.consultants.dto.ConsultantDto;
import com.neuromed.consultants.entity.Consultant;
import com.neuromed.consultants.exception.ResourceNotFoundException;
import com.neuromed.consultants.mapper.ConsultantMapper;
import com.neuromed.consultants.repository.ConsultantRepository;
import com.neuromed.consultants.service.IConsultantService;
import com.neuromed.consultants.service.client.UserFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ConsultantServiceImpl implements IConsultantService {

  private final ConsultantRepository consultantRepository;
  private final AppointmentClient appointmentClient;
  private final UserFeignClient userFeignClient;

  @Override
  public void createConsultant(ConsultantDto consultantDto) {
    Consultant consultant = ConsultantMapper.mapToConsultant(consultantDto);
    consultantRepository.save(consultant);
  }

  @Override
  public ConsultantDto fetchConsultant(Long consultantId) {
    Consultant consultant = consultantRepository.findById(consultantId)
        .orElseThrow(() -> new ResourceNotFoundException("Consultant", "id",
            consultantId.toString()));
    return ConsultantMapper.mapToConsultantDto(consultant);
  }

  @Override
  public boolean updateConsultant(ConsultantDto consultantDto) {
    Consultant consultant = consultantRepository.findById(consultantDto.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Consultant", "id",
            consultantDto.getId().toString()));
    consultant.setUserId(consultantDto.getUserId());
    consultant.setSpecialization(consultantDto.getSpecialization());
    consultant.setExperienceYears(consultantDto.getExperienceYears());
    consultant.setQualification(consultantDto.getQualification());
    consultant.setStatus(Consultant.Status.valueOf(consultantDto.getStatus()));
    consultantRepository.save(consultant);
    return true;
  }

  @Override
  public boolean deleteConsultant(Long consultantId) {
    if (!consultantRepository.existsById(consultantId)) {
      throw new ResourceNotFoundException("Consultant", "id", consultantId.toString());
    }
    consultantRepository.deleteById(consultantId);
    return true;
  }

  @Override
  public ConsultantDetailsDto fetchConsultantDetails(Long consultantId, String correlationId) {
    Consultant consultant = consultantRepository.findById(consultantId)
        .orElseThrow(() -> new ResourceNotFoundException("Consultant", "id",
            consultantId.toString()));
    ConsultantDetailsDto dto = new ConsultantDetailsDto();
    dto.setId(consultant.getId());
    dto.setSpecialization(consultant.getSpecialization());
    dto.setExperienceYears(consultant.getExperienceYears());
    dto.setQualification(consultant.getQualification());
    dto.setStatus(consultant.getStatus().name());
    ResponseEntity<UserModel> userModel = userFeignClient.fetchUserDetails(correlationId, consultant.getUserId().toString());
    dto.setUserModel(userModel.getBody());
    return dto;
  }

  @Override
  public List<ConsultantDetailsDto> listConsultants(String sortBy, String sortDir, String correlationId) {
    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();

    List<Consultant> consultants = consultantRepository.findAll(sort);

    return consultants.stream().map(consultant -> {
      ConsultantDetailsDto dto = new ConsultantDetailsDto();
      dto.setId(consultant.getId());
      dto.setSpecialization(consultant.getSpecialization());
      dto.setExperienceYears(consultant.getExperienceYears());
      dto.setQualification(consultant.getQualification());
      dto.setStatus(consultant.getStatus().name());

      try {
        // Safely call Feign client to get user info
        ResponseEntity<UserModel> userResponse =
                userFeignClient.fetchUserDetails(correlationId, consultant.getUserId().toString());
        dto.setUserModel(userResponse.getBody());
      } catch (Exception ex) {
        log.error("Failed to fetch user details for consultantId={}", consultant.getId(), ex);
        dto.setUserModel(null);
      }

      return dto;
    }).collect(Collectors.toList());
  }


  @Override
  public List<ConsultantDto> findAvailableConsultants(String visitType,
      LocalDate date) {
    if (visitType == null || date == null) {
      return Collections.emptyList();
    }

    List<AppointmentResponseDto> bookedAppointments = appointmentClient
        .getAppointmentsByVisitTypeAndDate(visitType, date);

    Set<Long> bookedConsultantIds = bookedAppointments.stream()
        .map(AppointmentResponseDto::getConsultantId)
        .filter(Objects::nonNull)
        .collect(Collectors.toSet());

    List<Consultant> consultants = consultantRepository
        .findByStatus(Consultant.Status.UNARCHIVED);

    return consultants.stream()
        .filter(c -> !bookedConsultantIds.contains(c.getId()))
        .map(ConsultantMapper::mapToConsultantDto)
        .collect(Collectors.toList());
  }
}

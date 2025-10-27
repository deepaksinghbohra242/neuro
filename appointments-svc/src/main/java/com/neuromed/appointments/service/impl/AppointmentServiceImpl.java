package com.neuromed.appointments.service.impl;

import com.neuromed.appointments.dto.*;
import com.neuromed.appointments.entity.Appointment;
import com.neuromed.appointments.exception.ResourceNotFoundException;
import com.neuromed.appointments.mapper.AppointmentMapper;
import com.neuromed.appointments.repository.AppointmentRepository;
import com.neuromed.appointments.service.IAppointmentService;
import com.neuromed.appointments.service.client.ConsultantsFeignClient;
import com.neuromed.appointments.service.client.PatientsFeignClient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements IAppointmentService {


    private AppointmentRepository appointmentRepository;
    private PatientsFeignClient patientsFeignClient;
    private ConsultantsFeignClient consultantsFeignClient;

    @Override
    public void createAppointment(AppointmentDTO appointmentDto) {
        if (appointmentDto.getTimeSlot() != null && appointmentDto.getEndTime() == null) {
            appointmentDto.setEndTime(appointmentDto.getTimeSlot().plusMinutes(getDefaultDuration(appointmentDto.getVisitType())));
        }

        Appointment appointment = AppointmentMapper.mapToAppointment(appointmentDto, new Appointment());
        appointmentRepository.save(appointment);
    }

    @Override
    public AppointmentDTO fetchAppointment(String correlationId, Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentId.toString()));

        AppointmentDTO appointmentDto = AppointmentMapper.mapToAppointmentDto(appointment, new AppointmentDTO());

        ResponseEntity<ConsultantDetailsDto> consultantResponse = consultantsFeignClient.fetchConsultantDetails(
                correlationId, appointment.getConsultantId());
        ConsultantDetailsDto consultant = consultantResponse.getBody();

        if (consultant != null) {
            appointmentDto.setUserModel(consultant.getUserModel());
        }

        return appointmentDto;
    }

    @Override
    public boolean updateAppointment(AppointmentDTO appointmentDto) {
        Appointment appointment = appointmentRepository.findById(appointmentDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentDto.getId().toString()));

        if (appointmentDto.getTimeSlot() != null && appointmentDto.getEndTime() == null) {
            appointmentDto.setEndTime(appointmentDto.getTimeSlot().plusMinutes(getDefaultDuration(appointmentDto.getVisitType())));
        }

        AppointmentMapper.mapToAppointment(appointmentDto, appointment);
        appointmentRepository.save(appointment);
        return true;
    }

    @Override
    public boolean deleteAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
        return true;
    }


    public AppointmentDetailsDTO fetchAppointmentDetails(Long appointmentId, String correlationId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentId.toString()));

        AppointmentDetailsDTO detailsDTO = new AppointmentDetailsDTO();
        detailsDTO.setAppointmentId(appointment.getId().toString());
        detailsDTO.setConsultantId(appointment.getConsultantId().toString());

        ResponseEntity<PatientDetailsDTO> patientResponse =
                patientsFeignClient.fetchPatientDetails(correlationId, appointment.getPatientId());
        if (patientResponse != null && patientResponse.getBody() != null) {
            PatientDetailsDTO patient = patientResponse.getBody();
            detailsDTO.setPatientDetails(patient);

            if (patient.getUserModel() != null) {
                detailsDTO.setPatientName(patient.getUserModel().getFirstName() + " " +
                        patient.getUserModel().getLastName());
            }
        }
        ResponseEntity<ConsultantDetailsDto> consultantResponse =
                consultantsFeignClient.fetchConsultantDetails(correlationId, appointment.getConsultantId());
        if (consultantResponse != null && consultantResponse.getBody() != null) {
            ConsultantDetailsDto consultant = consultantResponse.getBody();

            if (consultant.getUserModel() != null) {
                detailsDTO.setConsultantName(consultant.getUserModel().getFirstName() + " " +
                        consultant.getUserModel().getLastName());
            }
        }
        return detailsDTO;
    }

    @Override
    public List<AppointmentDTO> listAppointments(String correlationId) {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(appointment -> {
            AppointmentDTO dto = AppointmentMapper.mapToAppointmentDto(appointment, new AppointmentDTO());

            ResponseEntity<ConsultantDetailsDto> consultantResponse = consultantsFeignClient.fetchConsultantDetails(
                    correlationId, appointment.getConsultantId());
            ConsultantDetailsDto consultant = consultantResponse.getBody();
            if (consultant != null) {
                dto.setUserModel(consultant.getUserModel());
            }
            return dto;
        }).toList();
    }

    @Override
    public List<AppointmentDTO> listAppointmentsByConsultantId(String correlationId, Long consultantId) {
        List<Appointment> appointments = appointmentRepository.findByConsultantId(consultantId);
        return appointments.stream().map(appointment -> {
            AppointmentDTO dto = AppointmentMapper.mapToAppointmentDto(appointment, new AppointmentDTO());

            ResponseEntity<PatientDetailsDTO> patientsResponse = patientsFeignClient.fetchPatientDetails(
                    correlationId, appointment.getPatientId());
            PatientDetailsDTO patient = patientsResponse.getBody();
            if (patient != null) {
                dto.setPatientUserModel(patient.getUserModel());
            }
            return dto;
        }).toList();

    }


    private long getDefaultDuration(String visitType) {
        return switch (visitType != null ? visitType : "") {
            case "Consultation" -> 30;
            case "Follow-up" -> 15;
            case "Therapy" -> 60;
            default -> 30;
        };
    }
}

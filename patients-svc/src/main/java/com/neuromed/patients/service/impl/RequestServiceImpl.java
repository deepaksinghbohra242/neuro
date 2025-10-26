package com.neuromed.patients.service.impl;

import com.neuromed.patients.dto.AppointmentOtherDetailsDTO;
import com.neuromed.patients.dto.GpDetailsDTO;
import com.neuromed.patients.dto.MedicalDetailsDTO;
import com.neuromed.patients.dto.PatientDTO;
import com.neuromed.patients.dto.RequestDTO;
import com.neuromed.patients.dto.TransferDetailsDTO;
import com.neuromed.patients.entity.Request;
import com.neuromed.patients.exception.ResourceNotFoundException;
import com.neuromed.patients.mapper.RequestMapper;
import com.neuromed.patients.repository.RequestRepository;
import com.neuromed.patients.service.IRequestService;
import com.neuromed.patients.service.client.AppointmentFeignClient;
import com.neuromed.patients.service.client.PatientFeignClient;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements IRequestService {

    private final RequestRepository requestRepository;
    private final PatientFeignClient patientFeignClient;
    private final AppointmentFeignClient appointmentFeignClient;

    @Override
    public RequestDTO createRequest(RequestDTO dto) {
        Request entity = RequestMapper.toEntity(dto);
        Request saved = requestRepository.save(entity);
        return RequestMapper.toDTO(saved);
    }

    @Override
    public List<RequestDTO> getRequests(Long patientId) {
        return requestRepository.findByPatientId(patientId)
                .stream()
                .map(RequestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RequestDTO getRequestDetails(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "id", String.valueOf(requestId)));
        return RequestMapper.toDTO(request);
    }

    @Override
    public RequestDTO updateRequest(Long id, RequestDTO dto) {
        Request existing = requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "id", String.valueOf(id)));

        // Update only fields that can be changed
        if (dto.getRequestType() != null) {
            existing.setRequestType(Request.RequestType.valueOf(dto.getRequestType().toUpperCase().replace(" ", "_")));
        }
        if (dto.getStatus() != null) {
            existing.setStatus(Request.Status.valueOf(dto.getStatus().toUpperCase()));
        }
        existing.setDoctorName(dto.getDoctorName());
        existing.setDescription(dto.getDescription());
        existing.setReason(dto.getReason());
        existing.setDuration(dto.getDuration());
        existing.setDate(dto.getDate());
        existing.setConsultantId(dto.getConsultantId());

        Request updated = requestRepository.save(existing);
        return RequestMapper.toDTO(updated);
    }

    @Override
    public boolean deleteRequest(Long id) {
        if (!requestRepository.existsById(id)) {
            throw new ResourceNotFoundException("Request", "id", String.valueOf(id));
        }
        requestRepository.deleteById(id);
        return true;
    }

    @Override
    public List<RequestDTO> getAllTransfers() {
        return requestRepository.findAllTransfers()
                .stream()
                .map(RequestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransferDetailsDTO getTransferDetails(String transferId) {
        Request req = requestRepository.findByTransferId(transferId)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "transferId", transferId));

        // Fetch related data using Feign clients
        PatientDTO patient = patientFeignClient.getPatientById(req.getPatientId());
        MedicalDetailsDTO medical = patientFeignClient.getMedicalDetails(req.getPatientId());
        GpDetailsDTO gp = patientFeignClient.getGpDetails(req.getPatientId());
        AppointmentOtherDetailsDTO otherDetails = appointmentFeignClient.getAppointmentDetails(req.getId());

        return TransferDetailsDTO.builder()
                .transferId(req.getTransferId())
                .date(req.getDate().toLocalDate())
                .transferredBy(req.getDoctorName())
                .transferredTo(req.getTransferredTo())
                .reason(req.getReason())
                .status(req.getStatus().name())
                .patient(patient)
                .medicalOverview(medical)
                .gpDetails(gp)
                .appointmentOtherDetails(otherDetails)
                .build();
    }

    @Transactional
    @Override
    public boolean approveRequest(String transferId) {
        Request req = requestRepository.findByTransferId(transferId)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "transferId", transferId));

        // Approve request without requiring a reason
        req.setStatus(Request.Status.APPROVED);
        requestRepository.save(req);
        return true;
    }

    @Transactional
    @Override
    public boolean rejectRequest(Long id, String reason) {
        return updateStatus(id, Request.Status.REJECTED, reason);
    }

    private boolean updateStatus(Long id, Request.Status status, String reason) {
        Request req = requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request", "id", String.valueOf(id)));
        req.setStatus(status);
        req.setReason(reason);
        requestRepository.save(req);
        return true;
    }
}

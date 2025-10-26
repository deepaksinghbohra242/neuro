package com.neuromed.patients.service;

import com.neuromed.patients.dto.RequestDTO;
import com.neuromed.patients.dto.TransferDetailsDTO;

import java.util.List;

public interface IRequestService {
    RequestDTO createRequest(RequestDTO dto);
    List<RequestDTO> getRequests(Long patientId);
    RequestDTO getRequestDetails(Long id);
    RequestDTO updateRequest(Long id, RequestDTO dto);
    boolean deleteRequest(Long id);
    List<RequestDTO> getAllTransfers();
    TransferDetailsDTO getTransferDetails(String transferId);
    boolean approveRequest(String transferId);
    boolean rejectRequest(Long id, String reason);
}

package com.neuromed.consultants.service.client;

import com.neuromed.consultants.dto.PrescriptionDTO;

import com.neuromed.consultants.dto.PrescriptionDetailDTO;
import com.neuromed.consultants.dto.ReorderRequestDTO;
import com.neuromed.consultants.dto.ResponseDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PrescriptionClientFallback implements PrescriptionClient {

  @Override
  public List<PrescriptionDTO> getPrescriptions(String correlationId) {
    return null;
  }

  @Override
  public ResponseDto createPrescription(String correlationId, PrescriptionDTO dto) {
    return null;
  }

  @Override
  public PrescriptionDetailDTO getPrescriptionDetail(String correlationId, Long prescriptionId) {
    return null;
  }

  @Override
  public ResponseDto reorderPrescription(String correlationId, ReorderRequestDTO dto) {
    return null;
  }
}

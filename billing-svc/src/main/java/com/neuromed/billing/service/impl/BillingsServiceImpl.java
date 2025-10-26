package com.neuromed.billing.service.impl;

import com.neuromed.billing.service.client.AppointmentFeignClient;
import com.neuromed.billing.service.client.PatientsFeignClient;
import com.neuromed.billing.dto.AppointmentDTO;
import com.neuromed.billing.dto.BillingDetailDTO;
import com.neuromed.billing.dto.BillingsDTO;
import com.neuromed.billing.dto.PatientDTO;
import com.neuromed.billing.entity.Billings;
import com.neuromed.billing.exception.BillingsNotFoundException;
import com.neuromed.billing.mapper.BillingsMapper;
import com.neuromed.billing.repository.BillingsRepository;
import com.neuromed.billing.service.IBillingsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BillingsServiceImpl implements IBillingsService {

    private PatientsFeignClient patientsFeignClient;
    private AppointmentFeignClient appointmentFeignClient;

    private BillingsRepository billingsRepository;

    @Override
    public void createBillings(BillingsDTO dto) {
        Billings billings = BillingsMapper.mapToBillings(dto, new Billings());
        billingsRepository.save(billings);
    }

    @Override
    public BillingDetailDTO fetchBillings(Long id, String correlationId) {
        Billings billings = billingsRepository.findById(id)
                .orElseThrow(() -> new BillingsNotFoundException(id));
        BillingDetailDTO  detailDTO = new BillingDetailDTO();
        detailDTO.setId(billings.getId());
       ResponseEntity<PatientDTO> patientDTO = patientsFeignClient
               .fetchPatientDetails(correlationId, billings.getPatientId().toString());
        ResponseEntity<AppointmentDTO>  appointmentDTO = appointmentFeignClient
                .fetchAppointmentDetails(correlationId, billings.getAppointmentId().toString());
        detailDTO.setPatientDTO(patientDTO.getBody());
        detailDTO.setAppointmentDTO(appointmentDTO.getBody());
        return detailDTO;
    }

    @Override
    public List<BillingsDTO> getAllBillings() {
        return billingsRepository.findAll().stream()
                .map(b -> BillingsMapper.mapToBillingsDto(b, new BillingsDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public BillingsDTO getBillingById(Long id) {
        Billings billings = billingsRepository.findById(id)
                .orElseThrow(() -> new BillingsNotFoundException(id));
        return BillingsMapper.mapToBillingsDto(billings, new BillingsDTO());
    }

    @Override
    public void updateBillings(BillingsDTO dto) {
        Billings billings = billingsRepository.findById(dto.getId())
                .orElseThrow(() -> new BillingsNotFoundException(dto.getId()));
        BillingsMapper.mapToBillings(dto, billings);
        billingsRepository.save(billings);
    }
    @Override
    public void deleteBillings(Long id) {
        Billings billings = billingsRepository.findById(id)
                .orElseThrow(() -> new BillingsNotFoundException(id));
        billingsRepository.delete(billings);
    }

    @Override
    public List<BillingsDTO> getBillingsByAppointmentId(Long appointmentId) {
        List<Billings> billingsList = billingsRepository.findByAppointmentId(appointmentId);

        return billingsList.stream()
                .map(b -> BillingsMapper.mapToBillingsDto(b, new BillingsDTO()))
                .collect(Collectors.toList());
    }
}

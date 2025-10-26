package com.neuromed.pharmarcy.service.impl;

import com.neuromed.pharmarcy.dto.PrescriptionMedicineDTO;
import com.neuromed.pharmarcy.entity.PrescriptionMedicine;
import com.neuromed.pharmarcy.execption.ResourceNotFoundException;
import com.neuromed.pharmarcy.mapper.PrescriptionMedicineMapper;
import com.neuromed.pharmarcy.repository.PrescriptionMedicineRepository;
import com.neuromed.pharmarcy.service.IPrescriptionMedicineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PrescriptionMedicineServiceImpl implements IPrescriptionMedicineService {

    private final PrescriptionMedicineRepository pharmacyMedicineRepository;

    @Override
    public PrescriptionMedicineDTO createPrescriptionMedicine(PrescriptionMedicineDTO pharmacyMedicineDTO) {
        PrescriptionMedicine entity = PrescriptionMedicineMapper.mapToPrescriptionMedicine(pharmacyMedicineDTO);
        PrescriptionMedicine savedEntity = pharmacyMedicineRepository.save(entity);
        return PrescriptionMedicineMapper.mapToPrescriptionMedicineDTO(savedEntity);
    }

    @Override
    public PrescriptionMedicineDTO fetchPrescriptionMedicine(Long id) {
        PrescriptionMedicine entity = pharmacyMedicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedicine", "id", id.toString()));
        return PrescriptionMedicineMapper.mapToPrescriptionMedicineDTO(entity);
    }

    @Override
    public boolean updatePrescriptionMedicine(PrescriptionMedicineDTO pharmacyMedicineDTO) {
        if (pharmacyMedicineDTO.getId() == null) {
            return false;
        }

        PrescriptionMedicine existing = pharmacyMedicineRepository.findById(pharmacyMedicineDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("PrescriptionMedicine", "id", pharmacyMedicineDTO.getId().toString()));

        existing.setPrescriptionId(pharmacyMedicineDTO.getPrescriptionId());
        existing.setMedicineName(pharmacyMedicineDTO.getMedicineName());
        existing.setPrescribedDose(pharmacyMedicineDTO.getPrescribedDose());
        existing.setFrequency(pharmacyMedicineDTO.getFrequency());
        existing.setNoOfOrder(pharmacyMedicineDTO.getNoOfOrder());

        pharmacyMedicineRepository.save(existing);
        return true;
    }

    @Override
    public boolean deletePrescriptionMedicine(Long id) {
        if (!pharmacyMedicineRepository.existsById(id)) {
            throw new ResourceNotFoundException("PrescriptionMedicine", "id", id.toString());
        }
        pharmacyMedicineRepository.deleteById(id);
        return true;
    }

    @Override
    public PrescriptionMedicineDTO fetchPrescriptionMedicine(Long id, String correlationId) {
        // correlationId can be used for tracing/logging if needed
        PrescriptionMedicine entity = pharmacyMedicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PharmacyMedicine", "id", id.toString()));
        return PrescriptionMedicineMapper.mapToPrescriptionMedicineDTO(entity);
    }

    @Override
    public List<PrescriptionMedicineDTO> getAllPrescriptionMedicines() {
        return pharmacyMedicineRepository.findAll()
                .stream()
                .map(PrescriptionMedicineMapper::mapToPrescriptionMedicineDTO)
                .collect(Collectors.toList());
    }

    public List<PrescriptionMedicineDTO> getMedicinesByPrescriptionId(Long prescriptionId) {
        return pharmacyMedicineRepository.findByPrescriptionId(prescriptionId)
                .stream()
                .map(PrescriptionMedicineMapper::mapToPrescriptionMedicineDTO)
                .collect(Collectors.toList());
    }

}

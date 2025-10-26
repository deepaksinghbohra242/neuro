package com.neuromed.pharmarcy.mapper;

import com.neuromed.pharmarcy.dto.PrescriptionMedicineDTO;
import com.neuromed.pharmarcy.entity.PrescriptionMedicine;

public class PrescriptionMedicineMapper {
    public static PrescriptionMedicineDTO mapToPrescriptionMedicineDTO(PrescriptionMedicine entity) {
        if (entity == null) return null;

        PrescriptionMedicineDTO dto = new PrescriptionMedicineDTO();
        dto.setId(entity.getId());
        dto.setPrescriptionId(entity.getPrescriptionId());
        dto.setMedicineName(entity.getMedicineName());
        dto.setPrescribedDose(entity.getPrescribedDose());
        dto.setFrequency(entity.getFrequency());
        dto.setNoOfOrder(entity.getNoOfOrder());
        return dto;
    }

    public static PrescriptionMedicine mapToPrescriptionMedicine(PrescriptionMedicineDTO dto) {
        if (dto == null) return null;

        PrescriptionMedicine entity = new PrescriptionMedicine();
        entity.setId(dto.getId());
        entity.setPrescriptionId(dto.getPrescriptionId());
        entity.setMedicineName(dto.getMedicineName());
        entity.setPrescribedDose(dto.getPrescribedDose());
        entity.setFrequency(dto.getFrequency());
        entity.setNoOfOrder(dto.getNoOfOrder());
        return entity;
    }
}

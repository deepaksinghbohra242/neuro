package com.neuromed.pharmarcy.mapper;

import com.neuromed.pharmarcy.dto.PrescriptionDTO;
import com.neuromed.pharmarcy.entity.Prescription;

public class PrescriptionsMapper {

    public static PrescriptionDTO mapToPrescriptionsDTO(Prescription prescription) {
        if (prescription == null) {
            return null;
        }

        PrescriptionDTO dto = new PrescriptionDTO();
        dto.setId(prescription.getId());
        dto.setPatientId(prescription.getPatientId());
        dto.setConsultantId(prescription.getConsultantId());
        dto.setPharmacyId(prescription.getPharmacyId());
        dto.setDate(prescription.getDate());
        dto.setNoOfMedicines(prescription.getNoOfMedicines());
        dto.setDuration(prescription.getDuration());
        dto.setStatus(prescription.getStatus() != null ? prescription.getStatus().name() : null);

        Prescription.PreferredService service = prescription.getPreferredService();
        dto.setPreferredService(service != null ? service.getDisplayName() : null);

        return dto;
    }

    public static Prescription mapToPrescriptionsEntity(PrescriptionDTO prescriptionDTO) {
        if (prescriptionDTO == null) {
            return null;
        }

        Prescription entity = new Prescription();
        entity.setId(prescriptionDTO.getId());
        entity.setPatientId(prescriptionDTO.getPatientId());
        entity.setConsultantId(prescriptionDTO.getConsultantId());
        entity.setPharmacyId(prescriptionDTO.getPharmacyId());
        entity.setDate(prescriptionDTO.getDate());
        entity.setNoOfMedicines(prescriptionDTO.getNoOfMedicines());
        entity.setDuration(prescriptionDTO.getDuration());
        if (prescriptionDTO.getStatus() != null) {
            try {
                entity.setStatus(Prescription.Status.valueOf(prescriptionDTO.getStatus().trim().toUpperCase()));
            } catch (IllegalArgumentException e) {
                entity.setStatus(Prescription.Status.NEW);
            }
        } else {
            entity.setStatus(Prescription.Status.NEW);
        }


        String preferredServiceStr = prescriptionDTO.getPreferredService();
        if (preferredServiceStr != null) {
            for (Prescription.PreferredService service : Prescription.PreferredService.values()) {
                if (service.getDisplayName().equalsIgnoreCase(preferredServiceStr.trim())) {
                    entity.setPreferredService(service);
                    break;
                }
            }
        } else {
            entity.setPreferredService(null);
        }

        return entity;
    }
}

package com.neuromed.pharmarcy.mapper;

import com.neuromed.pharmarcy.dto.PharmacyDTO;
import com.neuromed.pharmarcy.entity.Pharmacy;

public class PharmacyMapper {


    public static PharmacyDTO mapToPharmacyDTO(Pharmacy pharmacy) {
        if (pharmacy == null) return null;
        PharmacyDTO dto = new PharmacyDTO();
        dto.setId(pharmacy.getId());
        dto.setUser_id(pharmacy.getUserId());
        dto.setLicense_number(pharmacy.getLicenseNumber());
        dto.setStatus(pharmacy.getStatus().name());

        return dto;
    }

    public static Pharmacy mapToPharmacy(PharmacyDTO dto) {
        if (dto == null) return null;

        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(dto.getId());
        pharmacy.setUserId(dto.getUser_id());
        pharmacy.setLicenseNumber(dto.getLicense_number());

        if (dto.getStatus() != null) {
            try {
                pharmacy.setStatus(Pharmacy.Status.valueOf(dto.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                pharmacy.setStatus(Pharmacy.Status.UNARCHIVE);
            }
        } else {
            pharmacy.setStatus(Pharmacy.Status.UNARCHIVE);
        }
        return pharmacy;
    }
}

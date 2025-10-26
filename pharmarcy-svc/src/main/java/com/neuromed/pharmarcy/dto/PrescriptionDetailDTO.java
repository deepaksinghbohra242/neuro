package com.neuromed.pharmarcy.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
public class PrescriptionDetailDTO {
    private Long id;
    private Long patientId;
    private Long consultantId;
    private Long pharmacyId;
    private Timestamp date;
    private Integer noOfMedicines;
    private Integer duration;
    private String preferredService;
    private List<PrescriptionMedicineDTO> medicines;
    private UserModel consultantUserModel;
    private UserModel patientUserModel;
}

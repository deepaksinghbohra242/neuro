package com.neuromed.pharmarcy.dto;

import lombok.*;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class PharmacyDTO {
    private Long id;
    private Long user_id;
    private String license_number;
    private String status;
    private UserModel pharmacyUserModel;
}

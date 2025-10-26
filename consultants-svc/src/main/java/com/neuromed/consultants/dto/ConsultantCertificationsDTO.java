package com.neuromed.consultants.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConsultantCertificationsDTO {
    private Long id;
    private Long consultantId;
    private String url;
}

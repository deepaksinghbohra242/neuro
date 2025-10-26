package com.neuromed.consultants.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "consultants")
@Getter
@Setter
@Data
public class ConsultantsContactInfoDto {
    private String email;
    private String phone;
    private String address;

    private Map<String, String> contactDetails;
    private List<String> onCallSupport;

}

package com.neuromed.patients.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "patients")
@Getter
@Setter
public class PatientsContactInfoDto {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> emergencyContacts;

}

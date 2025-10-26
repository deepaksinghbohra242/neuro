package com.neuromed.reports.mapper;

import com.neuromed.reports.dto.TestsDTO;
import com.neuromed.reports.entity.Tests;

public class TestsMapper {

    public static TestsDTO mapToTestsDto(Tests tests) {
        if (tests == null) return null;
        TestsDTO dto = new TestsDTO();
        dto.setId(tests.getId());
        dto.setName(tests.getName());
        dto.setDate(tests.getDate());
        dto.setPrescriptionId(tests.getPrescriptionId());
        dto.setConsultantId(tests.getConsultantId());
        dto.setAppointmentId(tests.getAppointmentId());
        return dto;
    }
    public static Tests mapToTests(TestsDTO dto) {
        if (dto == null) return null;
        Tests tests = new Tests();
        tests.setId(dto.getId());
        tests.setName(dto.getName());
        tests.setDate(dto.getDate());
        tests.setPrescriptionId(dto.getPrescriptionId());
        tests.setConsultantId(dto.getConsultantId());
        tests.setAppointmentId(dto.getAppointmentId());
        return tests;
    }
}

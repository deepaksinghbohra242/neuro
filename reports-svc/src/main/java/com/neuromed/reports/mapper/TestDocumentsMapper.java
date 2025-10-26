package com.neuromed.reports.mapper;
import com.neuromed.reports.dto.TestDocumentsDTO;
import com.neuromed.reports.entity.TestDocuments;

public class TestDocumentsMapper {

    public static TestDocumentsDTO mapToTestDocumentsDTO(TestDocuments entity) {
        if (entity == null) return null;

        TestDocumentsDTO dto = new TestDocumentsDTO();
        dto.setId(entity.getId());
        dto.setTestId(entity.getTestId());
        dto.setName(entity.getName());
        dto.setDate(entity.getDate());
        dto.setUrl(entity.getUrl());

        return dto;
    }

    public static TestDocuments mapToTestDocuments(TestDocumentsDTO dto) {
        if (dto == null) return null;

        TestDocuments entity = new TestDocuments();
        entity.setId(dto.getId());
        entity.setTestId(dto.getTestId());
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());

        return entity;
    }
}


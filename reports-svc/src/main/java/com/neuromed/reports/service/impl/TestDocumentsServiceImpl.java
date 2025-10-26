package com.neuromed.reports.service.impl;
import com.neuromed.reports.dto.TestDocumentsDTO;
import com.neuromed.reports.entity.TestDocuments;
import com.neuromed.reports.exception.ResourceNotFoundException;
import com.neuromed.reports.mapper.TestDocumentsMapper;
import com.neuromed.reports.repository.TestDocumentsRepository;
import com.neuromed.reports.service.ITestDocumentsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TestDocumentsServiceImpl implements ITestDocumentsService {

    private final TestDocumentsRepository testDocumentsRepository;

    @Override
    public TestDocumentsDTO createTestDocument(TestDocumentsDTO testDocumentsDTO) {
        TestDocuments document = TestDocumentsMapper.mapToTestDocuments(testDocumentsDTO);
        TestDocuments saved = testDocumentsRepository.save(document);
        return TestDocumentsMapper.mapToTestDocumentsDTO(saved);
    }

    @Override
    public List<TestDocumentsDTO> getAllTestDocuments() {
        return testDocumentsRepository.findAll()
                .stream()
                .map(TestDocumentsMapper::mapToTestDocumentsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TestDocumentsDTO> getDocumentsByTestId(Long testId) {
        return testDocumentsRepository.findById(testId)
                .stream()
                .map(TestDocumentsMapper::mapToTestDocumentsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TestDocumentsDTO updateTestDocument(Long id, TestDocumentsDTO testDocumentsDTO) {
        TestDocuments existing = testDocumentsRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setTestId(testDocumentsDTO.getTestId());
            existing.setName(testDocumentsDTO.getName());
            existing.setDate(testDocumentsDTO.getDate());
            existing.setUrl(testDocumentsDTO.getUrl());
            TestDocuments updated = testDocumentsRepository.save(existing);
            return TestDocumentsMapper.mapToTestDocumentsDTO(updated);
        }
        return null;
    }

    @Override
    public boolean deleteTestDocument(Long id) {
        if (testDocumentsRepository.existsById(id)) {
            testDocumentsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public TestDocumentsDTO getTestDocumentById(Long id) {
        TestDocuments document = testDocumentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TestDocuments", "id", id.toString()));
        return TestDocumentsMapper.mapToTestDocumentsDTO(document);
    }
}

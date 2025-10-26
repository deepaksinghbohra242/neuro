package com.neuromed.reports.service.impl;

import com.neuromed.reports.dto.TestsDTO;
import com.neuromed.reports.entity.Tests;
import com.neuromed.reports.exception.ResourceNotFoundException;
import com.neuromed.reports.mapper.TestsMapper;
import com.neuromed.reports.repository.TestsRepository;
import com.neuromed.reports.service.ITestsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestsServiceImpl implements ITestsService {

    private final TestsRepository testsRepository;

    @Override
    public void createTest(TestsDTO testsDTO) {
        Tests test = TestsMapper.mapToTests(testsDTO);
        testsRepository.save(test);
    }

    @Override
    public TestsDTO fetchTest(Long testId) {
        Tests test = testsRepository.findById(testId)
                .orElseThrow(() -> new ResourceNotFoundException("Test", "id", testId.toString()));
        return TestsMapper.mapToTestsDto(test);
    }

    @Override
    public boolean updateTest(TestsDTO testsDTO) {
        Tests test = testsRepository.findById(testsDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Test", "id", testsDTO.getId().toString()));
        Tests updatedTest = TestsMapper.mapToTests(testsDTO);
        updatedTest.setId(test.getId()); // Ensure the existing ID is preserved
        testsRepository.save(updatedTest);
        return true;
    }

    @Override
    public boolean deleteTest(Long testId) {
        if (!testsRepository.existsById(testId)) {
            throw new ResourceNotFoundException("Test", "id", testId.toString());
        }
        testsRepository.deleteById(testId);
        return true;
    }
    @Override
    public List<TestsDTO> getAllTests() {
        List<Tests> testsList = testsRepository.findAll();
        return testsList.stream()
                .map(TestsMapper::mapToTestsDto)
                .toList();
    }
    @Override
    public List<TestsDTO> getTestsByAppointmentId(Long appointmentId) {
        List<Tests> testsList = testsRepository.findByAppointmentId(appointmentId);
        return testsList.stream()
                .map(TestsMapper::mapToTestsDto)
                .toList();
    }

}

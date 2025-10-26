package com.neuromed.reports.service.impl;

import com.neuromed.reports.dto.DocumentDTO;
import com.neuromed.reports.dto.TestDTO;
import com.neuromed.reports.entity.Document;
import com.neuromed.reports.entity.Test;
import com.neuromed.reports.repository.DocumentRepository;
import com.neuromed.reports.repository.TestRepository;
import com.neuromed.reports.service.FileStorageService;
import com.neuromed.reports.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

  private final TestRepository testRepository;
  private final DocumentRepository documentRepository;
  private final FileStorageService fileStorageService;

  @Override
  public TestDTO createTest(TestDTO dto) {
    Test test = Test.builder()
        .name(dto.getName())
        .date(dto.getDate())
        .prescriptionId(dto.getPrescriptionId())
        .consultantId(dto.getConsultantId())
            .appointmentId(dto.getAppointmentId())
        .build();

    test = testRepository.save(test);
    dto.setId(test.getId());
    return dto;
  }

  @Override
  public List<TestDTO> getAllTests() {
    return testRepository.findAll().stream().map(test -> {
      TestDTO dto = new TestDTO();
      dto.setId(test.getId());
      dto.setName(test.getName());
      dto.setDate(test.getDate());
      dto.setConsultantId(test.getConsultantId());
      dto.setPrescriptionId(test.getPrescriptionId());
      dto.setAppointmentId(test.getAppointmentId());
      dto.setStatus(test.getStatus() != null ? test.getStatus() : Test.Status.PENDING);
      return dto;
    }).collect(Collectors.toList());
  }

  @Override
  public DocumentDTO uploadAttachment(Integer testId, MultipartFile file, String name) {
    Test test = testRepository.findById(testId)
        .orElseThrow(() -> new RuntimeException("Test not found"));

    try {
      Path uploadDir = Paths.get("uploads/");
      if (!Files.exists(uploadDir)) {
        Files.createDirectories(uploadDir);
      }

      String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
      Path filePath = uploadDir.resolve(fileName);
      Files.copy(file.getInputStream(), filePath);

      Document doc = Document.builder()
          .name(name != null ? name : file.getOriginalFilename())
          .date(LocalDate.now())
          .url(filePath.toString())
          .test(test)
          .build();

      doc = documentRepository.save(doc);

      DocumentDTO documentDTO = new DocumentDTO();
      documentDTO.setId(doc.getId());
      documentDTO.setName(doc.getName());
      documentDTO.setDate(doc.getDate());
      documentDTO.setUrl(doc.getUrl());
      return documentDTO;

    } catch (IOException e) {
      throw new RuntimeException("File upload failed", e);
    }
  }

  @Override
  public void deleteAttachment(Integer documentId) {
    documentRepository.deleteById(documentId);
  }

  @Override
  public DocumentDTO replaceAttachment(Integer documentId, DocumentDTO dto, MultipartFile file) {
    Document document = documentRepository.findById(documentId)
        .orElseThrow(() -> new RuntimeException("Document not found"));

    document.setName(dto.getName());
    document.setDate(dto.getDate());

    if (file != null && !file.isEmpty()) {
      String fileUrl = fileStorageService.storeFile(file);
      document.setUrl(fileUrl);
    }

    documentRepository.save(document);

    dto.setId(document.getId());
    dto.setUrl(document.getUrl());

    return dto;
  }

  @Override
  public TestDTO updateStatus(Integer testId, Test.Status status) {
    Test test = testRepository.findById(testId)
        .orElseThrow(() -> new IllegalArgumentException("Test not found with id: " + testId));
    test.setStatus(status);
    Test updatedTest = testRepository.save(test);

    return convertToDTO(updatedTest);
  }

  @Override
  public List<TestDTO> getTestsByAppointmentId(Long appointmentId) {
    return testRepository.findByAppointmentId(appointmentId).stream()
            .map(this::convertToDTO)
            .toList();
  }


  private TestDTO convertToDTO(Test test) {
    if (test == null)
      return null;

    TestDTO dto = new TestDTO();
    dto.setId(test.getId());
    dto.setName(test.getName());
    dto.setDate(test.getDate());
    dto.setConsultantId(test.getConsultantId());
    dto.setPrescriptionId(test.getPrescriptionId());
    dto.setAppointmentId(test.getAppointmentId());
    dto.setStatus(test.getStatus() != null ? test.getStatus() : Test.Status.PENDING);
    return dto;
  }
}

package com.neuromed.reports.service;

import com.neuromed.reports.dto.TestDTO;
import com.neuromed.reports.dto.DocumentDTO;
import com.neuromed.reports.entity.Test;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface TestService {
  TestDTO createTest(TestDTO dto);

  List<TestDTO> getAllTests();

  DocumentDTO uploadAttachment(Integer testId, MultipartFile file, String description);

  void deleteAttachment(Integer documentId);

  DocumentDTO replaceAttachment(Integer documentId, DocumentDTO dto, MultipartFile file);

  TestDTO updateStatus(Integer testId, Test.Status status);

  List<TestDTO> getTestsByAppointmentId(Long appointmentId);

}

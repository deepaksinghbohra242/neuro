package com.neuromed.consultants.service.client;

import com.neuromed.consultants.dto.TestListItemDTO;
import org.springframework.stereotype.Component;

@Component
public class TestClientFallback implements TestClient {

  @Override
  public TestListItemDTO createTest(Long patientId, TestListItemDTO testListItemDTO, String correlationId) {
    return null;
  }
}

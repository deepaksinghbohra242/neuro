package com.neuromed.consultants.service.client;

import com.neuromed.consultants.dto.TestListItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "reports", fallback = TestClientFallback.class)
public interface TestClient {

  @PostMapping(value = "/api/patients/{patientId}/tests", consumes = MediaType.APPLICATION_JSON_VALUE)
  TestListItemDTO createTest(@PathVariable("patientId") Long patientId,
      @RequestBody TestListItemDTO testListItemDTO,
      @RequestHeader("neuromed-correlation-id") String correlationId);
}

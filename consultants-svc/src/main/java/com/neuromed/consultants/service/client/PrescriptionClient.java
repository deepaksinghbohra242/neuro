package com.neuromed.consultants.service.client;

import com.neuromed.consultants.dto.PrescriptionDTO;
import com.neuromed.consultants.dto.PrescriptionDetailDTO;
import com.neuromed.consultants.dto.ReorderRequestDTO;
import com.neuromed.consultants.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "pharmarcy", path = "/api", fallback = PrescriptionClientFallback.class)
public interface PrescriptionClient {

        @GetMapping("/list")
        List<PrescriptionDTO> getPrescriptions(
                        @RequestHeader("neuromed-correlation-id") String correlationId);

        @PostMapping("/create")
        ResponseDto createPrescription(
                        @RequestHeader("neuromed-correlation-id") String correlationId,
                        @RequestBody PrescriptionDTO dto);

        @GetMapping("/detail/{id}")
        PrescriptionDetailDTO getPrescriptionDetail(
                        @RequestHeader("neuromed-correlation-id") String correlationId,
                        @PathVariable("id") Long id);

        @PostMapping("/reorder")
        ResponseDto reorderPrescription(
                        @RequestHeader("neuromed-correlation-id") String correlationId,
                        @RequestBody ReorderRequestDTO dto);
}

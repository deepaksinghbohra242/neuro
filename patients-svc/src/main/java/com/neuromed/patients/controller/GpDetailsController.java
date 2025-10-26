package com.neuromed.patients.controller;

import com.neuromed.patients.constants.GpDetailsConstants;
import com.neuromed.patients.dto.GpDetailsDTO;
import com.neuromed.patients.dto.ResponseDto;
import com.neuromed.patients.exception.ResourceNotFoundException;
import com.neuromed.patients.service.IGpDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gp-details")
@RequiredArgsConstructor
public class GpDetailsController {

    private final IGpDetailsService gpDetailsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createGpDetails(@RequestBody GpDetailsDTO dto) {
        gpDetailsService.createGpDetails(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(GpDetailsConstants.STATUS_201, GpDetailsConstants.MESSAGE_201_CREATE));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GpDetailsDTO> getGpDetails(@PathVariable Long id) {
        GpDetailsDTO details = gpDetailsService.getGpDetails(id);
        return ResponseEntity.ok(details);
    }

    @GetMapping
    public ResponseEntity<List<GpDetailsDTO>> getAllGpDetails() {
        return ResponseEntity.ok(gpDetailsService.getAllGpDetails());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateGpDetails(@PathVariable Long id, @RequestBody GpDetailsDTO dto) {
        gpDetailsService.updateGpDetails(id, dto);
        return ResponseEntity.ok(new ResponseDto(GpDetailsConstants.STATUS_200, GpDetailsConstants.MESSAGE_200_UPDATE));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteGpDetails(@PathVariable Long id) {
        gpDetailsService.deleteGpDetails(id);
        return ResponseEntity.ok(new ResponseDto(GpDetailsConstants.STATUS_200, GpDetailsConstants.MESSAGE_200_DELETE));
    }
}

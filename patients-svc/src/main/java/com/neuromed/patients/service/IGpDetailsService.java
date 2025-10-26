package com.neuromed.patients.service;

import com.neuromed.patients.dto.GpDetailsDTO;
import java.util.List;

public interface IGpDetailsService {
    GpDetailsDTO createGpDetails(GpDetailsDTO dto);
    GpDetailsDTO updateGpDetails(Long id, GpDetailsDTO dto);
    GpDetailsDTO getGpDetails(Long id);
    List<GpDetailsDTO> getAllGpDetails();
    boolean deleteGpDetails(Long id);
}

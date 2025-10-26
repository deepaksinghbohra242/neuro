package com.neuromed.patients.service;

import com.neuromed.patients.entity.Treatment;
import java.util.List;

public interface TreatmentService {
  Treatment createTreatment(Treatment treatment);

  List<Treatment> getAllTreatments();

  Treatment getTreatmentById(Long id);

  Treatment updateTreatment(Long id, Treatment treatment);

  boolean deleteTreatment(Long id);
}

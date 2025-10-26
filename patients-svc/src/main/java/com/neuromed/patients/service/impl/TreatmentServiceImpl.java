package com.neuromed.patients.service.impl;

import com.neuromed.patients.entity.Treatment;
import com.neuromed.patients.repository.TreatmentRepository;
import com.neuromed.patients.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentServiceImpl implements TreatmentService {

  @Autowired
  private TreatmentRepository treatmentRepository;

  @Override
  public Treatment createTreatment(Treatment treatment) {
    return treatmentRepository.save(treatment);
  }

  @Override
  public List<Treatment> getAllTreatments() {
    return treatmentRepository.findAll();
  }

  @Override
  public Treatment getTreatmentById(Long id) {
    return treatmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Treatment not found with id: " + id));
  }

  @Override
  public Treatment updateTreatment(Long id, Treatment treatmentDetails) {
    Treatment treatment = getTreatmentById(id);
    treatment.setTreatmentName(treatmentDetails.getTreatmentName());
    treatment.setLink(treatmentDetails.getLink());
    return treatmentRepository.save(treatment);
  }

  @Override
  public boolean deleteTreatment(Long id) {
    if (treatmentRepository.existsById(id)) {
      treatmentRepository.deleteById(id);
      return true;
    }
    return false;
  }
}

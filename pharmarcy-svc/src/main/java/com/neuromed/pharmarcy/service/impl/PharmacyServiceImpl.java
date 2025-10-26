package com.neuromed.pharmarcy.service.impl;

import com.neuromed.pharmarcy.dto.PharmacyDTO;
import com.neuromed.pharmarcy.dto.UserModel;
import com.neuromed.pharmarcy.entity.Pharmacy;
import com.neuromed.pharmarcy.execption.ResourceNotFoundException;
import com.neuromed.pharmarcy.mapper.PharmacyMapper;
import com.neuromed.pharmarcy.repository.PharmacyRepository;
import com.neuromed.pharmarcy.service.IPharmacyService;
import com.neuromed.pharmarcy.service.client.UserFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PharmacyServiceImpl implements IPharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final UserFeignClient userFeignClient;

    @Override
    public PharmacyDTO createPharmacy(PharmacyDTO pharmacyDTO) {
        Pharmacy pharmacy = PharmacyMapper.mapToPharmacy(pharmacyDTO);
        Pharmacy saved = pharmacyRepository.save(pharmacy);
        return PharmacyMapper.mapToPharmacyDTO(saved);
    }

    @Override
    public List<PharmacyDTO> getPharmacies() {
        return pharmacyRepository.findAll()
                .stream()
                .map(PharmacyMapper::mapToPharmacyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PharmacyDTO updatePharmacy(Long id, PharmacyDTO pharmacyDTO) {
        Pharmacy existing = pharmacyRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setUserId(pharmacyDTO.getUser_id());
            existing.setLicenseNumber(pharmacyDTO.getLicense_number());

            if (pharmacyDTO.getStatus() != null) {
                try {
                    existing.setStatus(Pharmacy.Status.valueOf(pharmacyDTO.getStatus().toUpperCase()));
                } catch (IllegalArgumentException e) {
                    existing.setStatus(Pharmacy.Status.UNARCHIVE);
                }
            }

            Pharmacy updated = pharmacyRepository.save(existing);
            return PharmacyMapper.mapToPharmacyDTO(updated);
        }
        return null;
    }

    @Override
    public boolean deletePharmacy(Long id) {
        if (pharmacyRepository.existsById(id)) {
            pharmacyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public PharmacyDTO fetchPharmacy(String pharmacyId, String correlationId) {
        Pharmacy pharmacy = pharmacyRepository.findById(Long.valueOf(pharmacyId))
                .orElseThrow(() -> new ResourceNotFoundException("Pharmacy", "id", pharmacyId));

        PharmacyDTO pharmacyDTO = PharmacyMapper.mapToPharmacyDTO(pharmacy);

        // ✅ Fetch user details from auth service
        ResponseEntity<UserModel> response = userFeignClient.fetchUserDetails(
                correlationId, String.valueOf(pharmacy.getUserId())
        );

        if (response != null && response.getBody() != null) {
            pharmacyDTO.setPharmacyUserModel(response.getBody());
        }

        return pharmacyDTO;
    }



}

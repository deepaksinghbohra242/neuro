package com.neuromed.patients.service.client;

import com.neuromed.patients.dto.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserFallback implements UserFeignClient {

    @Override
    public ResponseEntity<UserModel> fetchUserDetails(String correlationId, String userId) {
        return ResponseEntity.ok(null);
    }
}

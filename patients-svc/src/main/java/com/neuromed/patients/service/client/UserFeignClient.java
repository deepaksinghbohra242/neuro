package com.neuromed.patients.service.client;

import com.neuromed.patients.dto.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth", fallback = UserFallback.class)
public interface UserFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    ResponseEntity<UserModel> fetchUserDetails(
            @RequestHeader("neuromed-correlation-id") String correlationId,
            @RequestParam("userId") String userId);
}

package com.neuromed.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class JwtResponse {
	private final String jwtToken;
	private int statusCode;
	private String statusMsg;
	private LocalDateTime errorTime;
}

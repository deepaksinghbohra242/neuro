package com.neuromed.auth.entity;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class ErrorObject {
	
	private Integer statusCode;
	
	private String message;

	private LocalDateTime errorTime;
}

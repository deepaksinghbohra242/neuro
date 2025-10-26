package com.neuromed.patients.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FamilyProfileAlreadyExistsException extends RuntimeException {
    public FamilyProfileAlreadyExistsException(String message) {
        super(message);
    }
}

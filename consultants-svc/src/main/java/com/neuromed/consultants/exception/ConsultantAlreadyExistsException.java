package com.neuromed.consultants.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ConsultantAlreadyExistsException extends RuntimeException {

    public ConsultantAlreadyExistsException(String message) {
        super(message);
    }

}

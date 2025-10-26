package com.neuromed.reports.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ReportsAlreadyExistsException extends RuntimeException {

    public ReportsAlreadyExistsException(String message) {
        super(message);
    }

}
